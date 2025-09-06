package com.ruoyi.web.controller.common;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MinioUtils;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    
    @Autowired
    private MinioUtils minioUtils;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 使用MinIO上传文件
            String url = minioUtils.uploadFile(file);
            // 从URL中提取文件名
            String fileName = extractFileNameFromUrl(url);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            log.error("文件上传失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @ResponseBody
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 使用MinIO上传文件
                String url = minioUtils.uploadFile(file);
                // 从URL中提取文件名
                String fileName = extractFileNameFromUrl(url);
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            log.error("文件上传失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + FileUtils.stripPrefix(resource);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
    
    /**
     * 通用文件删除请求
     * 
     * @param key 文件URL或文件路径
     * @return 删除结果
     */
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult deleteFile(String key)
    {
        try
        {
            if (StringUtils.isEmpty(key))
            {
                return AjaxResult.error("文件路径不能为空");
            }
            
            // 从URL中提取文件路径
            String fileName = extractFileNameFromUrl(key);
            
            if (StringUtils.isEmpty(fileName))
            {
                return AjaxResult.error("无法解析文件路径");
            }
            
            // 删除MinIO中的文件
            minioUtils.deleteFile(fileName);
            log.info("文件删除成功: {}", fileName);
            return AjaxResult.success("文件删除成功");
        }
        catch (Exception e)
        {
            log.error("删除文件时发生异常: {}", e.getMessage(), e);
            return AjaxResult.error("删除文件时发生异常: " + e.getMessage());
        }
    }

    /**
     * 从MinIO URL中提取文件名
     * 
     * @param url MinIO文件URL
     * @return 文件名
     */
    private String extractFileNameFromUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        try {
            // MinIO URL格式: http://localhost:9000/bucket-name/path/filename?params
            // 提取文件路径部分（去掉参数）
            String urlWithoutParams = url.split("\\?")[0];
            // 提取存储桶名称后的路径部分
            int bucketIndex = urlWithoutParams.indexOf("/", urlWithoutParams.indexOf("://") + 3);
            if (bucketIndex != -1) {
                int pathIndex = urlWithoutParams.indexOf("/", bucketIndex + 1);
                if (pathIndex != -1) {
                    return urlWithoutParams.substring(pathIndex + 1);
                }
            }
            return urlWithoutParams.substring(urlWithoutParams.lastIndexOf("/") + 1);
        } catch (Exception e) {
            log.warn("提取文件名失败，使用完整URL: {}", url);
            return url;
        }
    }
}
