package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MailTemplatesMapper;
import com.ruoyi.system.domain.MailTemplates;
import com.ruoyi.system.service.IMailTemplatesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 邮件模板Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-06
 */
@Service
public class MailTemplatesServiceImpl implements IMailTemplatesService 
{
    @Autowired
    private MailTemplatesMapper mailTemplatesMapper;

    /**
     * 查询邮件模板
     * 
     * @param templateId 邮件模板主键
     * @return 邮件模板
     */
    @Override
    public MailTemplates selectMailTemplatesByTemplateId(Long templateId)
    {
        return mailTemplatesMapper.selectMailTemplatesByTemplateId(templateId);
    }

    /**
     * 查询邮件模板列表
     * 
     * @param mailTemplates 邮件模板
     * @return 邮件模板
     */
    @Override
    public List<MailTemplates> selectMailTemplatesList(MailTemplates mailTemplates)
    {
        return mailTemplatesMapper.selectMailTemplatesList(mailTemplates);
    }

    /**
     * 新增邮件模板
     * 
     * @param mailTemplates 邮件模板
     * @return 结果
     */
    @Override
    public int insertMailTemplates(MailTemplates mailTemplates)
    {
        return mailTemplatesMapper.insertMailTemplates(mailTemplates);
    }

    /**
     * 修改邮件模板
     * 
     * @param mailTemplates 邮件模板
     * @return 结果
     */
    @Override
    public int updateMailTemplates(MailTemplates mailTemplates)
    {
        return mailTemplatesMapper.updateMailTemplates(mailTemplates);
    }

    /**
     * 批量删除邮件模板
     * 
     * @param templateIds 需要删除的邮件模板主键
     * @return 结果
     */
    @Override
    public int deleteMailTemplatesByTemplateIds(String templateIds)
    {
        return mailTemplatesMapper.deleteMailTemplatesByTemplateIds(Convert.toStrArray(templateIds));
    }

    /**
     * 删除邮件模板信息
     * 
     * @param templateId 邮件模板主键
     * @return 结果
     */
    @Override
    public int deleteMailTemplatesByTemplateId(Long templateId)
    {
        return mailTemplatesMapper.deleteMailTemplatesByTemplateId(templateId);
    }
}
