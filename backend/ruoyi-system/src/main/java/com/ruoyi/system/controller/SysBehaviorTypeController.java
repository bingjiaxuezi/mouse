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
import com.ruoyi.system.domain.SysBehaviorType;
import com.ruoyi.system.service.ISysBehaviorTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行为类型字典Controller
 * 
 * @author lczj
 * @date 2025-09-20
 */
@Controller
@RequestMapping("/system/SysBehaviorType")
public class SysBehaviorTypeController extends BaseController
{
    private String prefix = "system/SysBehaviorType";

    @Autowired
    private ISysBehaviorTypeService sysBehaviorTypeService;

    @RequiresPermissions("system:SysBehaviorType:view")
    @GetMapping()
    public String SysBehaviorType()
    {
        return prefix + "/SysBehaviorType";
    }

    /**
     * 查询行为类型字典列表
     */
    @RequiresPermissions("system:SysBehaviorType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBehaviorType sysBehaviorType)
    {
        startPage();
        List<SysBehaviorType> list = sysBehaviorTypeService.selectSysBehaviorTypeList(sysBehaviorType);
        return getDataTable(list);
    }

    /**
     * 导出行为类型字典列表
     */
    @RequiresPermissions("system:SysBehaviorType:export")
    @Log(title = "行为类型字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBehaviorType sysBehaviorType)
    {
        List<SysBehaviorType> list = sysBehaviorTypeService.selectSysBehaviorTypeList(sysBehaviorType);
        ExcelUtil<SysBehaviorType> util = new ExcelUtil<SysBehaviorType>(SysBehaviorType.class);
        return util.exportExcel(list, "行为类型字典数据");
    }

    /**
     * 新增行为类型字典
     */
    @RequiresPermissions("system:SysBehaviorType:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存行为类型字典
     */
    @RequiresPermissions("system:SysBehaviorType:add")
    @Log(title = "行为类型字典", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBehaviorType sysBehaviorType)
    {
        return toAjax(sysBehaviorTypeService.insertSysBehaviorType(sysBehaviorType));
    }

    /**
     * 修改行为类型字典
     */
    @RequiresPermissions("system:SysBehaviorType:edit")
    @GetMapping("/edit/{behaviorTypeId}")
    public String edit(@PathVariable("behaviorTypeId") Long behaviorTypeId, ModelMap mmap)
    {
        SysBehaviorType sysBehaviorType = sysBehaviorTypeService.selectSysBehaviorTypeByBehaviorTypeId(behaviorTypeId);
        mmap.put("sysBehaviorType", sysBehaviorType);
        return prefix + "/edit";
    }

    /**
     * 修改保存行为类型字典
     */
    @RequiresPermissions("system:SysBehaviorType:edit")
    @Log(title = "行为类型字典", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBehaviorType sysBehaviorType)
    {
        return toAjax(sysBehaviorTypeService.updateSysBehaviorType(sysBehaviorType));
    }

    /**
     * 删除行为类型字典
     */
    @RequiresPermissions("system:SysBehaviorType:remove")
    @Log(title = "行为类型字典", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBehaviorTypeService.deleteSysBehaviorTypeByBehaviorTypeIds(ids));
    }
}
