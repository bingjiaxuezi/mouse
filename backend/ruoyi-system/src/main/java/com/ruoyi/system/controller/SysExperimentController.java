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
import com.ruoyi.system.domain.SysExperiment;
import com.ruoyi.system.service.ISysExperimentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实验详情展示Controller
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
@Controller
@RequestMapping("/system/SysExperiment")
public class SysExperimentController extends BaseController
{
    private String prefix = "system/SysExperiment";

    @Autowired
    private ISysExperimentService sysExperimentService;

    @RequiresPermissions("system:SysExperiment:view")
    @GetMapping()
    public String SysExperiment()
    {
        return prefix + "/SysExperiment";
    }

    /**
     * 查询实验详情展示列表
     */
    @RequiresPermissions("system:SysExperiment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysExperiment sysExperiment)
    {
        startPage();
        List<SysExperiment> list = sysExperimentService.selectSysExperimentList(sysExperiment);
        return getDataTable(list);
    }

    /**
     * 导出实验详情展示列表
     */
    @RequiresPermissions("system:SysExperiment:export")
    @Log(title = "实验详情展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysExperiment sysExperiment)
    {
        List<SysExperiment> list = sysExperimentService.selectSysExperimentList(sysExperiment);
        ExcelUtil<SysExperiment> util = new ExcelUtil<SysExperiment>(SysExperiment.class);
        return util.exportExcel(list, "实验详情展示数据");
    }

    /**
     * 新增实验详情展示
     */
    @RequiresPermissions("system:SysExperiment:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实验详情展示
     */
    @RequiresPermissions("system:SysExperiment:add")
    @Log(title = "实验详情展示", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysExperiment sysExperiment)
    {
        return toAjax(sysExperimentService.insertSysExperiment(sysExperiment));
    }

    /**
     * 修改实验详情展示
     */
    @RequiresPermissions("system:SysExperiment:edit")
    @GetMapping("/edit/{experimentId}")
    public String edit(@PathVariable("experimentId") Long experimentId, ModelMap mmap)
    {
        SysExperiment sysExperiment = sysExperimentService.selectSysExperimentByExperimentId(experimentId);
        mmap.put("sysExperiment", sysExperiment);
        return prefix + "/edit";
    }

    /**
     * 修改保存实验详情展示
     */
    @RequiresPermissions("system:SysExperiment:edit")
    @Log(title = "实验详情展示", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysExperiment sysExperiment)
    {
        return toAjax(sysExperimentService.updateSysExperiment(sysExperiment));
    }

    /**
     * 删除实验详情展示
     */
    @RequiresPermissions("system:SysExperiment:remove")
    @Log(title = "实验详情展示", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysExperimentService.deleteSysExperimentByExperimentIds(ids));
    }
}
