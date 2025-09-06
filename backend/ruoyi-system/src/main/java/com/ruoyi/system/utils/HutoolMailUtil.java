package com.ruoyi.system.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.MailTemplates;
import com.ruoyi.system.service.IMailTemplatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 邮件发送工具类
 */
@Component
public class HutoolMailUtil {

    // 邮件配置
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.ssl-enable}")
    private Boolean sslEnable;

    @Value("${mail.from-name}")
    private String fromName;

    @Value("${mail.from-address}")
    private String fromAddress;

    @Value("${mail.retry.max-attempts:3}")
    private Integer maxAttempts;

    @Value("${mail.retry.initial-delay:1000}")
    private Long initialDelay;

    @Value("${mail.retry.multiplier:2}")
    private Integer multiplier;
    
    @Value("${mail.retry.max-delay:10000}")
    private Long maxDelay;
    
    @Value("${mail.connection.timeout:30000}")
    private Integer connectionTimeout;
    
    @Value("${mail.connection.read-timeout:60000}")
    private Integer readTimeout;
    
    @Value("${mail.connection.write-timeout:60000}")
    private Integer writeTimeout;
    
    @Value("${mail.send.debug:false}")
    private Boolean debugMode;
    
    @Value("${mail.send.default-encoding:UTF-8}")
    private String defaultEncoding;
    
    @Value("${mail.starttls-enable:false}")
    private Boolean starttlsEnable;

    private static MailAccount mailAccount;
    private static HutoolMailUtil instance;

    @PostConstruct
    public void init() {
        mailAccount = new MailAccount();
        mailAccount.setHost(host);
        mailAccount.setPort(port);
        mailAccount.setUser(username);
        mailAccount.setPass(password);
        mailAccount.setFrom(StrUtil.format("{} <{}>", fromName, fromAddress));
        mailAccount.setSslEnable(sslEnable);
        mailAccount.setStarttlsEnable(starttlsEnable);
        mailAccount.setAuth(true);
        
        // 设置连接超时
        mailAccount.setTimeout(connectionTimeout);
        mailAccount.setConnectionTimeout(readTimeout);
        
        // 设置编码
        mailAccount.setCharset(Charset.forName(defaultEncoding));
        
        // 设置调试模式
        if (debugMode != null && debugMode) {
            System.setProperty("mail.debug", "true");
        }
        
        // 设置静态实例
        instance = this;
    }
    
    /**
     * 获取邮件模板服务
     */
    private static IMailTemplatesService getMailTemplatesService() {
        return SpringUtils.getBean(IMailTemplatesService.class);
    }

    /**
     * 发送邮件给单个收件人（静态方法）
     * @param templateId 模板ID
     * @param recipient 收件人邮箱
     * @param params 邮件参数（用于替换模板中的占位符）
     * @throws Exception 发送失败异常
     */
    public static void sendMail(Long templateId, String recipient, Object... params) throws Exception {
        if (mailAccount == null) {
            throw new RuntimeException("邮件配置未初始化，请检查邮件服务配置");
        }
        
        if (recipient == null || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("收件人邮箱不能为空");
        }
        
        // 获取邮件模板
        IMailTemplatesService mailTemplatesService = getMailTemplatesService();
        MailTemplates template = mailTemplatesService.selectMailTemplatesByTemplateId(templateId);
        if (template == null) {
            throw new RuntimeException("邮件模板不存在，templateId: " + templateId);
        }

        if (template.getIsActive() == 0) {
            throw new RuntimeException("邮件模板已被禁用，templateId: " + templateId);
        }

        // 组装邮件主题和内容
        String subject = template.getSubject();
        String content = template.getContent();

        // 使用参数替换模板中的占位符
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                String placeholder = "{" + i + "}";
                String value = params[i] != null ? params[i].toString() : "";
                subject = subject.replace(placeholder, value);
                content = content.replace(placeholder, value);
            }
        }

        // 指数退避算法发送邮件
        sendWithExponentialBackoff(recipient, subject, content);
    }

    /**
     * 使用指数退避算法发送邮件
     * @param recipient 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws Exception 发送失败异常
     */
    private static void sendWithExponentialBackoff(String recipient, String subject, String content) throws Exception {
        Exception lastException = null;
        long delay = instance.initialDelay;
        int maxAttempts = instance.maxAttempts;
        int multiplier = instance.multiplier;
        long maxDelay = instance.maxDelay;

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                // 发送邮件
                MailUtil.send(mailAccount, recipient, subject, content, true);
                // 发送成功，直接返回
                return;
            } catch (Exception e) {
                lastException = e;
                // 如果不是最后一次尝试，则等待后重试
                if (attempt < maxAttempts - 1) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(delay);
                        delay *= multiplier; // 指数增长延迟时间
                        // 确保延迟时间不超过最大延迟
                        if (delay > maxDelay) {
                            delay = maxDelay;
                        }
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("邮件发送被中断", ie);
                    }
                }
            }
        }

        // 所有重试都失败了，抛出最后一个异常
        throw new RuntimeException("邮件发送失败，已重试 " + maxAttempts + " 次", lastException);
    }
    
    /**
     * 获取邮件配置信息（用于调试）
     */
    public static String getMailConfigInfo() {
        if (mailAccount == null) {
            return "邮件配置未初始化";
        }
        return String.format("邮件服务器: %s:%d, 用户: %s, SSL: %s", 
            mailAccount.getHost(), mailAccount.getPort(), 
            mailAccount.getUser(), mailAccount.isSslEnable());
    }
}
