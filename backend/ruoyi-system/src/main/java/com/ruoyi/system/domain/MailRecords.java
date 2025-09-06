package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件发送记录对象 mail_records
 * 
 * @author lczj
 * @date 2025-09-06
 */
public class MailRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邮件ID */
    private Long mailId;

    /** 关联的模板ID */
    @Excel(name = "关联的模板ID")
    private Long templateId;

    /** 发送者用户ID */
    @Excel(name = "发送者用户ID")
    private Long senderId;

    /** 发送者名称 */
    @Excel(name = "发送者名称")
    private String senderName;

    /** 发送者邮箱 */
    @Excel(name = "发送者邮箱")
    private String senderEmail;

    /** 实际发送的邮件主题 */
    @Excel(name = "实际发送的邮件主题")
    private String subject;

    /** 实际发送的邮件内容 */
    @Excel(name = "实际发送的邮件内容")
    private String content;

    /** 邮件状态 */
    @Excel(name = "邮件状态")
    private String status;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 发送结果详情JSON格式存储错误信息等 */
    @Excel(name = "发送结果详情JSON格式存储错误信息等")
    private String resultInfo;

    /** 邮件模板信息 */
    private List<MailTemplates> mailTemplatesList;

    public void setMailId(Long mailId) 
    {
        this.mailId = mailId;
    }

    public Long getMailId() 
    {
        return mailId;
    }

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }

    public void setSenderName(String senderName) 
    {
        this.senderName = senderName;
    }

    public String getSenderName() 
    {
        return senderName;
    }

    public void setSenderEmail(String senderEmail) 
    {
        this.senderEmail = senderEmail;
    }

    public String getSenderEmail() 
    {
        return senderEmail;
    }

    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }

    public void setResultInfo(String resultInfo) 
    {
        this.resultInfo = resultInfo;
    }

    public String getResultInfo() 
    {
        return resultInfo;
    }

    public List<MailTemplates> getMailTemplatesList()
    {
        return mailTemplatesList;
    }

    public void setMailTemplatesList(List<MailTemplates> mailTemplatesList)
    {
        this.mailTemplatesList = mailTemplatesList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mailId", getMailId())
            .append("templateId", getTemplateId())
            .append("senderId", getSenderId())
            .append("senderName", getSenderName())
            .append("senderEmail", getSenderEmail())
            .append("subject", getSubject())
            .append("content", getContent())
            .append("status", getStatus())
            .append("sendTime", getSendTime())
            .append("resultInfo", getResultInfo())
            .append("mailTemplatesList", getMailTemplatesList())
            .toString();
    }
}
