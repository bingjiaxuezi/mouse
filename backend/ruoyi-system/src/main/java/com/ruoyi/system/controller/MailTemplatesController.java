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
import com.ruoyi.system.domain.MailTemplates;
import com.ruoyi.system.service.IMailTemplatesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 邮件模板Controller
 * 
 * @author lczj
 * @date 2025-09-06
 */
@Controller
@RequestMapping("/mail/template")
public class MailTemplatesController extends BaseController
{
    private String prefix = "mail/template";

    @Autowired
    private IMailTemplatesService mailTemplatesService;

    @RequiresPermissions("system:templates:view")
    @GetMapping()
    public String templates()
    {
        return prefix + "/templates";
    }

    /**
     * 查询邮件模板列表
     */
    @RequiresPermissions("system:templates:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MailTemplates mailTemplates)
    {
        startPage();
        List<MailTemplates> list = mailTemplatesService.selectMailTemplatesList(mailTemplates);
        return getDataTable(list);
    }

    /**
     * 导出邮件模板列表
     */
    @RequiresPermissions("system:templates:export")
    @Log(title = "邮件模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MailTemplates mailTemplates)
    {
        List<MailTemplates> list = mailTemplatesService.selectMailTemplatesList(mailTemplates);
        ExcelUtil<MailTemplates> util = new ExcelUtil<MailTemplates>(MailTemplates.class);
        return util.exportExcel(list, "邮件模板数据");
    }

    /**
     * 新增邮件模板
     */
    @RequiresPermissions("system:templates:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存邮件模板
     */
    @RequiresPermissions("system:templates:add")
    @Log(title = "邮件模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MailTemplates mailTemplates)
    {
        return toAjax(mailTemplatesService.insertMailTemplates(mailTemplates));
    }

    /**
     * 修改邮件模板
     */
    @RequiresPermissions("system:templates:edit")
    @GetMapping("/edit/{templateId}")
    public String edit(@PathVariable("templateId") Long templateId, ModelMap mmap)
    {
        MailTemplates mailTemplates = mailTemplatesService.selectMailTemplatesByTemplateId(templateId);
        mmap.put("mailTemplates", mailTemplates);
        return prefix + "/edit";
    }

    /**
     * 修改保存邮件模板
     */
    @RequiresPermissions("system:templates:edit")
    @Log(title = "邮件模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MailTemplates mailTemplates)
    {
        return toAjax(mailTemplatesService.updateMailTemplates(mailTemplates));
    }

    /**
     * 删除邮件模板
     */
    @RequiresPermissions("system:templates:remove")
    @Log(title = "邮件模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mailTemplatesService.deleteMailTemplatesByTemplateIds(ids));
    }
}
