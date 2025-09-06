package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MailTemplates;

/**
 * 邮件模板Service接口
 * 
 * @author lczj
 * @date 2025-09-06
 */
public interface IMailTemplatesService 
{
    /**
     * 查询邮件模板
     * 
     * @param templateId 邮件模板主键
     * @return 邮件模板
     */
    public MailTemplates selectMailTemplatesByTemplateId(Long templateId);

    /**
     * 查询邮件模板列表
     * 
     * @param mailTemplates 邮件模板
     * @return 邮件模板集合
     */
    public List<MailTemplates> selectMailTemplatesList(MailTemplates mailTemplates);

    /**
     * 新增邮件模板
     * 
     * @param mailTemplates 邮件模板
     * @return 结果
     */
    public int insertMailTemplates(MailTemplates mailTemplates);

    /**
     * 修改邮件模板
     * 
     * @param mailTemplates 邮件模板
     * @return 结果
     */
    public int updateMailTemplates(MailTemplates mailTemplates);

    /**
     * 批量删除邮件模板
     * 
     * @param templateIds 需要删除的邮件模板主键集合
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateIds(String templateIds);

    /**
     * 删除邮件模板信息
     * 
     * @param templateId 邮件模板主键
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateId(Long templateId);
}
