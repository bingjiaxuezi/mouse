package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MailRecipients;

/**
 * 邮件接收者关联Service接口
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
public interface IMailRecipientsService 
{
    /**
     * 查询邮件接收者关联
     * 
     * @param id 邮件接收者关联主键
     * @return 邮件接收者关联
     */
    public MailRecipients selectMailRecipientsById(Long id);

    /**
     * 查询邮件接收者关联列表
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 邮件接收者关联集合
     */
    public List<MailRecipients> selectMailRecipientsList(MailRecipients mailRecipients);

    /**
     * 新增邮件接收者关联
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 结果
     */
    public int insertMailRecipients(MailRecipients mailRecipients);

    /**
     * 修改邮件接收者关联
     * 
     * @param mailRecipients 邮件接收者关联
     * @return 结果
     */
    public int updateMailRecipients(MailRecipients mailRecipients);

    /**
     * 批量删除邮件接收者关联
     * 
     * @param ids 需要删除的邮件接收者关联主键集合
     * @return 结果
     */
    public int deleteMailRecipientsByIds(String ids);

    /**
     * 删除邮件接收者关联信息
     * 
     * @param id 邮件接收者关联主键
     * @return 结果
     */
    public int deleteMailRecipientsById(Long id);
}
