package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.MailTemplates;
import com.ruoyi.system.mapper.MailRecordsMapper;
import com.ruoyi.system.domain.MailRecords;
import com.ruoyi.system.service.IMailRecordsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 邮件发送记录Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-06
 */
@Service
public class MailRecordsServiceImpl implements IMailRecordsService 
{
    @Autowired
    private MailRecordsMapper mailRecordsMapper;

    /**
     * 查询邮件发送记录
     * 
     * @param mailId 邮件发送记录主键
     * @return 邮件发送记录
     */
    @Override
    public MailRecords selectMailRecordsByMailId(Long mailId)
    {
        return mailRecordsMapper.selectMailRecordsByMailId(mailId);
    }

    /**
     * 查询邮件发送记录列表
     * 
     * @param mailRecords 邮件发送记录
     * @return 邮件发送记录
     */
    @Override
    public List<MailRecords> selectMailRecordsList(MailRecords mailRecords)
    {
        return mailRecordsMapper.selectMailRecordsList(mailRecords);
    }

    /**
     * 新增邮件发送记录
     * 
     * @param mailRecords 邮件发送记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMailRecords(MailRecords mailRecords)
    {
        int rows = mailRecordsMapper.insertMailRecords(mailRecords);
        insertMailTemplates(mailRecords);
        return rows;
    }

    /**
     * 修改邮件发送记录
     * 
     * @param mailRecords 邮件发送记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMailRecords(MailRecords mailRecords)
    {
        mailRecordsMapper.deleteMailTemplatesByTemplateId(mailRecords.getMailId());
        insertMailTemplates(mailRecords);
        return mailRecordsMapper.updateMailRecords(mailRecords);
    }

    /**
     * 批量删除邮件发送记录
     * 
     * @param mailIds 需要删除的邮件发送记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMailRecordsByMailIds(String mailIds)
    {
        mailRecordsMapper.deleteMailTemplatesByTemplateIds(Convert.toStrArray(mailIds));
        return mailRecordsMapper.deleteMailRecordsByMailIds(Convert.toStrArray(mailIds));
    }

    /**
     * 删除邮件发送记录信息
     * 
     * @param mailId 邮件发送记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMailRecordsByMailId(Long mailId)
    {
        mailRecordsMapper.deleteMailTemplatesByTemplateId(mailId);
        return mailRecordsMapper.deleteMailRecordsByMailId(mailId);
    }

    /**
     * 新增邮件模板信息
     * 
     * @param mailRecords 邮件发送记录对象
     */
    public void insertMailTemplates(MailRecords mailRecords)
    {
        List<MailTemplates> mailTemplatesList = mailRecords.getMailTemplatesList();
        Long mailId = mailRecords.getMailId();
        if (StringUtils.isNotNull(mailTemplatesList))
        {
            List<MailTemplates> list = new ArrayList<MailTemplates>();
            for (MailTemplates mailTemplates : mailTemplatesList)
            {
                mailTemplates.setTemplateId(mailId);
                list.add(mailTemplates);
            }
            if (list.size() > 0)
            {
                mailRecordsMapper.batchMailTemplates(list);
            }
        }
    }
}
