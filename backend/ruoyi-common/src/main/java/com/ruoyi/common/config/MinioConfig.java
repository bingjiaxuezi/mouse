package com.ruoyi.common.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO配置类
 * 
 * @author ruoyi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    
    /**
     * MinIO服务端点
     */
    private String endpoint = "http://localhost:9000";
    
    /**
     * MinIO访问密钥
     */
    private String accessKey = "minioadmin";
    
    /**
     * MinIO秘密密钥
     */
    private String secretKey = "minioadmin123";
    
    /**
     * 默认存储桶名称
     */
    private String bucketName = "mouse-images";
    
    /**
     * 创建MinIO客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}