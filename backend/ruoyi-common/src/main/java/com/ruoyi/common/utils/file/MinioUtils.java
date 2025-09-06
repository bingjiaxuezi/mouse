package com.ruoyi.common.utils.file;

import com.ruoyi.common.config.MinioConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * MinIO工具类
 * 
 * @author ruoyi
 */
@Slf4j
@Component
public class MinioUtils {
    
    @Autowired
    private MinioClient minioClient;
    
    @Autowired
    private MinioConfig minioConfig;
    
    /**
     * 初始化，创建存储桶
     */
    @PostConstruct
    public void init() {
        try {
            createBucketIfNotExists(minioConfig.getBucketName());
        } catch (Exception e) {
            log.error("初始化MinIO存储桶失败", e);
        }
    }
    
    /**
     * 创建存储桶（如果不存在）
     */
    public void createBucketIfNotExists(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("创建存储桶: {}", bucketName);
        }
    }
    
    /**
     * 上传文件
     * 
     * @param file 上传的文件
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file) throws Exception {
        return uploadFile(file, minioConfig.getBucketName());
    }
    
    /**
     * 上传文件到指定存储桶
     * 
     * @param file 上传的文件
     * @param bucketName 存储桶名称
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 生成文件名
        String fileName = generateFileName(file.getOriginalFilename());
        
        // 上传文件
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build()
        );
        
        // 返回文件访问URL
        return getFileUrl(bucketName, fileName);
    }
    
    /**
     * 上传文件流
     * 
     * @param inputStream 文件流
     * @param fileName 文件名
     * @param contentType 文件类型
     * @return 文件访问URL
     */
    public String uploadFile(InputStream inputStream, String fileName, String contentType) throws Exception {
        return uploadFile(inputStream, fileName, contentType, minioConfig.getBucketName());
    }
    
    /**
     * 上传文件流到指定存储桶
     * 
     * @param inputStream 文件流
     * @param fileName 文件名
     * @param contentType 文件类型
     * @param bucketName 存储桶名称
     * @return 文件访问URL
     */
    public String uploadFile(InputStream inputStream, String fileName, String contentType, String bucketName) throws Exception {
        // 生成文件名
        String objectName = generateFileName(fileName);
        
        // 上传文件
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(inputStream, -1, 10485760) // 10MB
                .contentType(contentType)
                .build()
        );
        
        // 返回文件访问URL
        return getFileUrl(bucketName, objectName);
    }
    
    /**
     * 删除文件
     * 
     * @param fileName 文件名
     */
    public void deleteFile(String fileName) throws Exception {
        deleteFile(fileName, minioConfig.getBucketName());
    }
    
    /**
     * 删除指定存储桶中的文件
     * 
     * @param fileName 文件名
     * @param bucketName 存储桶名称
     */
    public void deleteFile(String fileName, String bucketName) throws Exception {
        minioClient.removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build()
        );
    }
    
    /**
     * 获取文件访问URL
     * 
     * @param fileName 文件名
     * @return 文件访问URL
     */
    public String getFileUrl(String fileName) throws Exception {
        return getFileUrl(minioConfig.getBucketName(), fileName);
    }
    
    /**
     * 获取文件访问URL
     * 
     * @param bucketName 存储桶名称
     * @param fileName 文件名
     * @return 文件访问URL
     */
    public String getFileUrl(String bucketName, String fileName) throws Exception {
        // 由于bucket已设置为公开访问，直接返回公开URL
        String publicUrl = minioConfig.getEndpoint() + "/" + bucketName + "/" + fileName;
        
        // 替换内部容器地址为外部可访问地址
        return replaceInternalUrl(publicUrl);
    }
    
    /**
     * 替换内部容器地址为外部可访问地址
     * 
     * @param url 原始URL
     * @return 替换后的URL
     */
    private String replaceInternalUrl(String url) {
        if (url != null && url.contains("minio:9000")) {
            // 替换内部容器地址为外部地址
            return url.replace("minio:9000", "localhost:9000");
        }
        return url;
    }
    
    /**
     * 获取文件下载流
     * 
     * @param fileName 文件名
     * @return 文件流
     */
    public InputStream getFileStream(String fileName) throws Exception {
        return getFileStream(fileName, minioConfig.getBucketName());
    }
    
    /**
     * 获取文件下载流
     * 
     * @param fileName 文件名
     * @param bucketName 存储桶名称
     * @return 文件流
     */
    public InputStream getFileStream(String fileName, String bucketName) throws Exception {
        return minioClient.getObject(
            GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build()
        );
    }
    
    /**
     * 生成文件名
     * 
     * @param originalFilename 原始文件名
     * @return 生成的文件名
     */
    private String generateFileName(String originalFilename) {
        // 获取文件扩展名
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        
        // 生成文件名：日期/UUID.扩展名
        String datePath = DateUtils.datePath();
        String uuid = IdUtils.fastUUID();
        
        return datePath + "/" + uuid + extension;
    }
}