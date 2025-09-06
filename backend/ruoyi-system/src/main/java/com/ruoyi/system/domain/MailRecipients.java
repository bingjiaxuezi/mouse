package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件接收者关联对象 mail_recipients
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
public class MailRecipients extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    private Long id;

    /** 邮件模板ID */
    @Excel(name = "邮件模板ID")
    private Long templateId;

    /** 接收者用户ID */
    @Excel(name = "接收者用户ID")
    private Long recipientId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 是否启用: 1-启用, 0-禁用 */
    @Excel(name = "是否启用: 1-启用, 0-禁用")
    private Long isActive;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setRecipientId(Long recipientId) 
    {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() 
    {
        return recipientId;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setIsActive(Long isActive) 
    {
        this.isActive = isActive;
    }

    public Long getIsActive() 
    {
        return isActive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("recipientId", getRecipientId())
            .append("createdAt", getCreatedAt())
            .append("isActive", getIsActive())
            .toString();
    }
}
