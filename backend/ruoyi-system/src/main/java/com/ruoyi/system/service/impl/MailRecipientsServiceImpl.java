package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MailRecipientsMapper;
import com.ruoyi.system.domain.MailRecipients;
import com.ruoyi.system.service.IMailRecipientsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 邮件接收者关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
@Service
public class MailRecipientsServiceImpl implements IMailRecipientsService 
{
    @Autowired
    private MailRecipientsMapper mailRecipientsMapper;

    /**
     * 查询邮件接收者关联
     * 
     * @param id 邮件接收者关联主键
     * @return 邮件接收者关联
     */
    @Override
    public MailRecipients selectMailRecipientsById(Long id)
    {
        return mailRecipientsMapper.selectMailRecipientsById(id);
    }

    /**
     * 查询邮件接收者关联列表
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 邮件接收者关联
     */
    @Override
    public List<MailRecipients> selectMailRecipientsList(MailRecipients mailRecipients)
    {
        return mailRecipientsMapper.selectMailRecipientsList(mailRecipients);
    }

    /**
     * 新增邮件接收者关联
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 结果
     */
    @Override
    public int insertMailRecipients(MailRecipients mailRecipients)
    {
        return mailRecipientsMapper.insertMailRecipients(mailRecipients);
    }

    /**
     * 修改邮件接收者关联
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 结果
     */
    @Override
    public int updateMailRecipients(MailRecipients mailRecipients)
    {
        return mailRecipientsMapper.updateMailRecipients(mailRecipients);
    }

    /**
     * 批量删除邮件接收者关联
     * 
     * @param ids 需要删除的邮件接收者关联主键
     * @return 结果
     */
    @Override
    public int deleteMailRecipientsByIds(String ids)
    {
        return mailRecipientsMapper.deleteMailRecipientsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除邮件接收者关联信息
     * 
     * @param id 邮件接收者关联主键
     * @return 结果
     */
    @Override
    public int deleteMailRecipientsById(Long id)
    {
        return mailRecipientsMapper.deleteMailRecipientsById(id);
    }
}
