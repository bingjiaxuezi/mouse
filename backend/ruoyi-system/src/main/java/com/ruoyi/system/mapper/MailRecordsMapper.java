package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MailRecords;
import com.ruoyi.system.domain.MailTemplates;

/**
 * 邮件发送记录Mapper接口
 * 
 * @author lczj
 * @date 2025-09-06
 */
public interface MailRecordsMapper 
{
    /**
     * 查询邮件发送记录
     * 
     * @param mailId 邮件发送记录主键
     * @return 邮件发送记录
     */
    public MailRecords selectMailRecordsByMailId(Long mailId);

    /**
     * 查询邮件发送记录列表
     * 
     * @param mailRecords 邮件发送记录
     * @return 邮件发送记录集合
     */
    public List<MailRecords> selectMailRecordsList(MailRecords mailRecords);

    /**
     * 新增邮件发送记录
     * 
     * @param mailRecords 邮件发送记录
     * @return 结果
     */
    public int insertMailRecords(MailRecords mailRecords);

    /**
     * 修改邮件发送记录
     * 
     * @param mailRecords 邮件发送记录
     * @return 结果
     */
    public int updateMailRecords(MailRecords mailRecords);

    /**
     * 删除邮件发送记录
     * 
     * @param mailId 邮件发送记录主键
     * @return 结果
     */
    public int deleteMailRecordsByMailId(Long mailId);

    /**
     * 批量删除邮件发送记录
     * 
     * @param mailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailRecordsByMailIds(String[] mailIds);

    /**
     * 批量删除邮件模板
     * 
     * @param mailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateIds(String[] mailIds);
    
    /**
     * 批量新增邮件模板
     * 
     * @param mailTemplatesList 邮件模板列表
     * @return 结果
     */
    public int batchMailTemplates(List<MailTemplates> mailTemplatesList);
    

    /**
     * 通过邮件发送记录主键删除邮件模板信息
     * 
     * @param mailId 邮件发送记录ID
     * @return 结果
     */
    public int deleteMailTemplatesByTemplateId(Long mailId);
}
