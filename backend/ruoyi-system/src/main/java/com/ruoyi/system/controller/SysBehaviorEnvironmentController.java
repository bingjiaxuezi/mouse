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
import com.ruoyi.system.domain.SysBehaviorEnvironment;
import com.ruoyi.system.service.ISysBehaviorEnvironmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行为事件环境数据Controller
 * 
 * @author lczj
 * @date 2025-10-07
 */
@Controller
@RequestMapping("/system/SysBehaviorEnvironment")
public class SysBehaviorEnvironmentController extends BaseController
{
    private String prefix = "system/SysBehaviorEnvironment";

    @Autowired
    private ISysBehaviorEnvironmentService sysBehaviorEnvironmentService;

    @RequiresPermissions("system:SysBehaviorEnvironment:view")
    @GetMapping()
    public String SysBehaviorEnvironment()
    {
        return prefix + "/SysBehaviorEnvironment";
    }

    /**
     * 查询行为事件环境数据列表
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        startPage();
        List<SysBehaviorEnvironment> list = sysBehaviorEnvironmentService.selectSysBehaviorEnvironmentList(sysBehaviorEnvironment);
        return getDataTable(list);
    }

    /**
     * 导出行为事件环境数据列表
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:export")
    @Log(title = "行为事件环境数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        List<SysBehaviorEnvironment> list = sysBehaviorEnvironmentService.selectSysBehaviorEnvironmentList(sysBehaviorEnvironment);
        ExcelUtil<SysBehaviorEnvironment> util = new ExcelUtil<SysBehaviorEnvironment>(SysBehaviorEnvironment.class);
        return util.exportExcel(list, "行为事件环境数据数据");
    }

    /**
     * 新增行为事件环境数据
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存行为事件环境数据
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:add")
    @Log(title = "行为事件环境数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        return toAjax(sysBehaviorEnvironmentService.insertSysBehaviorEnvironment(sysBehaviorEnvironment));
    }

    /**
     * 修改行为事件环境数据
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:edit")
    @GetMapping("/edit/{envId}")
    public String edit(@PathVariable("envId") Long envId, ModelMap mmap)
    {
        SysBehaviorEnvironment sysBehaviorEnvironment = sysBehaviorEnvironmentService.selectSysBehaviorEnvironmentByEnvId(envId);
        mmap.put("sysBehaviorEnvironment", sysBehaviorEnvironment);
        return prefix + "/edit";
    }

    /**
     * 修改保存行为事件环境数据
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:edit")
    @Log(title = "行为事件环境数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        return toAjax(sysBehaviorEnvironmentService.updateSysBehaviorEnvironment(sysBehaviorEnvironment));
    }

    /**
     * 删除行为事件环境数据
     */
    @RequiresPermissions("system:SysBehaviorEnvironment:remove")
    @Log(title = "行为事件环境数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBehaviorEnvironmentService.deleteSysBehaviorEnvironmentByEnvIds(ids));
    }
}
