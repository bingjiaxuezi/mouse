package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MailRecords;

/**
 * 邮件发送记录Service接口
 * 
 * @author lczj
 * @date 2025-09-06
 */
public interface IMailRecordsService 
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
     * 批量删除邮件发送记录
     * 
     * @param mailIds 需要删除的邮件发送记录主键集合
     * @return 结果
     */
    public int deleteMailRecordsByMailIds(String mailIds);

    /**
     * 删除邮件发送记录信息
     * 
     * @param mailId 邮件发送记录主键
     * @return 结果
     */
    public int deleteMailRecordsByMailId(Long mailId);
}
