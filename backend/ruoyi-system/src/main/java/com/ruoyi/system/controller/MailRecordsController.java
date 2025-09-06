package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MailRecords;
import com.ruoyi.system.service.IMailRecordsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 邮件发送记录Controller
 * 
 * @author lczj
 * @date 2025-09-06
 */
@Controller
@RequestMapping("/system/records")
public class MailRecordsController extends BaseController
{
    private String prefix = "system/records";

    @Autowired
    private IMailRecordsService mailRecordsService;

    @RequiresPermissions("system:records:view")
    @GetMapping()
    public String records()
    {
        return prefix + "/records";
    }

    /**
     * 查询邮件发送记录列表
     */
    @RequiresPermissions("system:records:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MailRecords mailRecords)
    {
        startPage();
        List<MailRecords> list = mailRecordsService.selectMailRecordsList(mailRecords);
        return getDataTable(list);
    }

    /**
     * 导出邮件发送记录列表
     */
    @RequiresPermissions("system:records:export")
    @Log(title = "邮件发送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MailRecords mailRecords)
    {
        List<MailRecords> list = mailRecordsService.selectMailRecordsList(mailRecords);
        ExcelUtil<MailRecords> util = new ExcelUtil<MailRecords>(MailRecords.class);
        return util.exportExcel(list, "邮件发送记录数据");
    }

    /**
     * 新增邮件发送记录
     */
    @RequiresPermissions("system:records:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存邮件发送记录
     */
    @RequiresPermissions("system:records:add")
    @Log(title = "邮件发送记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MailRecords mailRecords)
    {
        return toAjax(mailRecordsService.insertMailRecords(mailRecords));
    }

    /**
     * 修改邮件发送记录
     */
    @RequiresPermissions("system:records:edit")
    @GetMapping("/edit/{mailId}")
    public String edit(@PathVariable("mailId") Long mailId, ModelMap mmap)
    {
        MailRecords mailRecords = mailRecordsService.selectMailRecordsByMailId(mailId);
        mmap.put("mailRecords", mailRecords);
        return prefix + "/edit";
    }

    /**
     * 修改保存邮件发送记录
     */
    @RequiresPermissions("system:records:edit")
    @Log(title = "邮件发送记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MailRecords mailRecords)
    {
        return toAjax(mailRecordsService.updateMailRecords(mailRecords));
    }

    /**
     * 删除邮件发送记录
     */
    @RequiresPermissions("system:records:remove")
    @Log(title = "邮件发送记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mailRecordsService.deleteMailRecordsByMailIds(ids));
    }
}
