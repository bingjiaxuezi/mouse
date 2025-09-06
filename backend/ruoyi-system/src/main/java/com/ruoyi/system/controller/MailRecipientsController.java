package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.MailTemplates;
import com.ruoyi.system.domain.MailRecords;
import com.ruoyi.system.service.IMailTemplatesService;
import com.ruoyi.system.service.IMailRecordsService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.utils.HutoolMailUtil;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MailRecipients;
import com.ruoyi.system.service.IMailRecipientsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 邮件接收者关联Controller
 *
 * @author ruoyi
 * @date 2025-09-06
 */
@Controller
@RequestMapping("/system/recipients")
public class MailRecipientsController extends BaseController
{
    private String prefix = "system/recipients";

    @Autowired
    private IMailRecipientsService mailRecipientsService;

    @Autowired
    private IMailTemplatesService mailTemplatesService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IMailRecordsService mailRecordsService;

    @RequiresPermissions("system:recipients:view")
    @GetMapping()
    public String recipients()
    {
        return prefix + "/recipients";
    }

    /**
     * 查询邮件接收者关联列表
     */
    @RequiresPermissions("system:recipients:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MailRecipients mailRecipients)
    {
        startPage();
        List<MailRecipients> list = mailRecipientsService.selectMailRecipientsList(mailRecipients);
        return getDataTable(list);
    }

    /**
     * 导出邮件接收者关联列表
     */
    @RequiresPermissions("system:recipients:export")
    @Log(title = "邮件接收者关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MailRecipients mailRecipients)
    {
        List<MailRecipients> list = mailRecipientsService.selectMailRecipientsList(mailRecipients);
        ExcelUtil<MailRecipients> util = new ExcelUtil<MailRecipients>(MailRecipients.class);
        return util.exportExcel(list, "邮件接收者关联数据");
    }

    /**
     * 新增邮件接收者关联
     */
    @RequiresPermissions("system:recipients:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存邮件接收者关联
     */
    @RequiresPermissions("system:recipients:add")
    @Log(title = "邮件接收者关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MailRecipients mailRecipients)
    {
        return toAjax(mailRecipientsService.insertMailRecipients(mailRecipients));
    }

    /**
     * 修改邮件接收者关联
     */
    @RequiresPermissions("system:recipients:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MailRecipients mailRecipients = mailRecipientsService.selectMailRecipientsById(id);
        mmap.put("mailRecipients", mailRecipients);
        return prefix + "/edit";
    }

    /**
     * 修改保存邮件接收者关联
     */
    @RequiresPermissions("system:recipients:edit")
    @Log(title = "邮件接收者关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MailRecipients mailRecipients)
    {
        return toAjax(mailRecipientsService.updateMailRecipients(mailRecipients));
    }

    /**
     * 根据模板ID查询邮件接收者关联列表
     */
    @RequiresPermissions("system:recipients:list")
    @PostMapping("/listByTemplate")
    @ResponseBody
    public TableDataInfo listByTemplate(Long templateId)
    {
        MailRecipients mailRecipients = new MailRecipients();
        mailRecipients.setTemplateId(templateId);
        startPage();
        List<MailRecipients> list = mailRecipientsService.selectMailRecipientsList(mailRecipients);
        return getDataTable(list);
    }

/**
 * 批量新增邮件接收者关联
 */
@RequiresPermissions("system:recipients:add")
@Log(title = "邮件接收者关联", businessType = BusinessType.INSERT)
@PostMapping("/batchAdd")
@ResponseBody
public AjaxResult batchAdd(Long templateId, String recipientIds)
{
    if (templateId == null || recipientIds == null || recipientIds.trim().isEmpty()) {
        return AjaxResult.error("参数不能为空");
    }

    String[] ids = recipientIds.split(",");
    int successCount = 0;
    int alreadyExistsCount = 0;
    int failedCount = 0;

    for (String recipientId : ids) {
        if (recipientId != null && !recipientId.trim().isEmpty()) {
            try {
                MailRecipients mailRecipients = new MailRecipients();
                mailRecipients.setTemplateId(templateId);
                mailRecipients.setRecipientId(Long.valueOf(recipientId.trim()));

                // 检查是否已存在
                List<MailRecipients> existingList = mailRecipientsService.selectMailRecipientsList(mailRecipients);
                if (existingList.isEmpty()) {
                    // 不存在则新增
                    mailRecipients.setIsActive(1L);
                    int result = mailRecipientsService.insertMailRecipients(mailRecipients);
                    if (result > 0) {
                        successCount++;
                    } else {
                        failedCount++;
                    }
                } else {
                    // 已存在，检查状态
                    MailRecipients existing = existingList.get(0);
                    if (existing.getIsActive() == 0) {
                        // 如果已存在但被禁用，则启用它
                        existing.setIsActive(1L);
                        int result = mailRecipientsService.updateMailRecipients(existing);
                        if (result > 0) {
                            successCount++;
                        } else {
                            failedCount++;
                        }
                    } else {
                        // 已存在且已启用
                        alreadyExistsCount++;
                    }
                }
            } catch (NumberFormatException e) {
                failedCount++;
                logger.error("无效的recipientId格式: " + recipientId, e);
            } catch (Exception e) {
                failedCount++;
                logger.error("处理recipientId时发生错误: " + recipientId, e);
            }
        }
    }

    StringBuilder message = new StringBuilder();
    message.append("批量添加完成: ");
    if (successCount > 0) {
        message.append("成功添加 ").append(successCount).append(" 个收件人");
    }
    if (alreadyExistsCount > 0) {
        if (successCount > 0) message.append(", ");
        message.append(alreadyExistsCount).append(" 个收件人已存在");
    }
    if (failedCount > 0) {
        if (successCount > 0 || alreadyExistsCount > 0) message.append(", ");
        message.append(failedCount).append(" 个收件人添加失败");
    }

    // 只要有一个成功或已存在就不算完全失败
    if (successCount + alreadyExistsCount > 0) {
        return AjaxResult.success(message.toString());
    } else {
        return AjaxResult.error(message.toString());
    }
}

    /**
     * 删除邮件接收者关联
     */
    @RequiresPermissions("system:recipients:remove")
    @Log(title = "邮件接收者关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mailRecipientsService.deleteMailRecipientsByIds(ids));
    }

    /**
     * 测试接口，模拟发送邮件，入参是模板id
     */
    @RequiresPermissions("system:recipients:test")
    @PostMapping("/test")
    @Log(title = "邮件发送测试", businessType = BusinessType.OTHER)
    @ResponseBody
    public AjaxResult test(@RequestParam("templateId") Long templateId) {
        try {
            // Step 1: 根据模板id获取模板信息
            MailTemplates mailTemplate = mailTemplatesService.selectMailTemplatesByTemplateId(templateId);
            if (mailTemplate == null) {
                return AjaxResult.error("邮件模板不存在，templateId: " + templateId);
            }

            // Step 2: 根据模板id获取邮件接收者列表
            MailRecipients mailRecipients = new MailRecipients();
            mailRecipients.setTemplateId(templateId);
            List<MailRecipients> mailRecipientsList = mailRecipientsService.selectMailRecipientsList(mailRecipients);

            if (mailRecipientsList.isEmpty()) {
                return AjaxResult.error("该模板暂无绑定收件人");
            }



            // Step 4: 循环发送给每个收件人并记录
            int successCount = 0;
            int failCount = 0;
            List<String> failedRecipients = new ArrayList<>();

            for (MailRecipients mailRecipient : mailRecipientsList) {
                SysUser user = sysUserService.selectUserById(mailRecipient.getRecipientId());
                if (user == null || user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                    continue; // 跳过无效用户
                }

                // 为每个收件人创建单独的邮件记录
                MailRecords mailRecord = new MailRecords();
                mailRecord.setTemplateId(templateId);
                mailRecord.setSenderId(user.getUserId());
                mailRecord.setSenderName(user.getUserName());
                mailRecord.setSenderEmail(user.getEmail());
                mailRecord.setSubject(mailTemplate.getSubject());
                mailRecord.setContent(mailTemplate.getContent());
                mailRecord.setSendTime(new Date());

                String status = "sent";
                String resultInfo = "";

                try {
                    // 发送邮件给单个收件人
                    HutoolMailUtil.sendMail(templateId, user.getEmail());

                    // 发送成功，记录详细信息
                    ObjectMapper mapper = new ObjectMapper();
                    resultInfo = mapper.writeValueAsString(new java.util.HashMap<String, Object>() {{
                        put("recipientEmail", user.getEmail());
                        put("recipientName", user.getUserName());
                        put("sendTime", new Date());
                        put("message", "邮件发送成功");
                    }});

                    successCount++;

                } catch (Exception e) {
                    status = "failed";
                    failCount++;
                    failedRecipients.add(user.getUserName() + "(" + user.getEmail() + ")");

                    // 记录失败原因
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        resultInfo = mapper.writeValueAsString(new java.util.HashMap<String, Object>() {{
                            put("recipientEmail", user.getEmail());
                            put("recipientName", user.getUserName());
                            put("error", e.getMessage());
                            put("errorType", e.getClass().getSimpleName());
                            put("sendTime", new Date());
                        }});
                    } catch (Exception jsonException) {
                        resultInfo = "邮件发送失败: " + e.getMessage();
                    }
                }

                // 记录每个收件人的发送结果
                mailRecord.setStatus(status);
                mailRecord.setResultInfo(resultInfo);

                try {
                    mailRecordsService.insertMailRecords(mailRecord);
                } catch (Exception recordException) {
                    logger.error("记录邮件发送信息时出错: {}", recordException.getMessage());
                }
            }

            // 返回总体发送结果
            if (failCount == 0) {
                return AjaxResult.success("邮件发送成功，共发送 " + successCount + " 封邮件");
            } else if (successCount == 0) {
                return AjaxResult.error("邮件发送全部失败，失败 " + failCount + " 封邮件。失败收件人: " + String.join(", ", failedRecipients));
            } else {
                return AjaxResult.success("邮件发送部分成功，成功 " + successCount + " 封，失败 " + failCount + " 封。失败收件人: " + String.join(", ", failedRecipients));
            }

        } catch (Exception e) {
            logger.error("邮件发送测试异常: {}", e.getMessage(), e);
            return AjaxResult.error("邮件发送测试异常: " + e.getMessage());
        }
    }
}
