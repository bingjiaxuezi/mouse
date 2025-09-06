package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MailTemplates;

/**
 * 邮件模板Mapper接口
 * 
 * @author lczj
 * @date 2025-09-06
 */
public interface MailTemplatesMapper 
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
     * @param mailTaemplates 邮件模板
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
     * 删除邮件模板
     * 
     * @param templateId 邮件模板主键
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateId(Long templateId);

    /**
     * 批量删除邮件模板
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateIds(String[] templateIds);
}
