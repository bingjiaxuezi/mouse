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
import com.ruoyi.system.domain.SysCageMouse;
import com.ruoyi.system.service.ISysCageMouseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 笼子-小鼠关系Controller
 * 
 * @author lczj
 * @date 2025-09-14
 */
@Controller
@RequestMapping("/system/SysCageMouse")
public class SysCageMouseController extends BaseController
{
    private String prefix = "system/SysCageMouse";

    @Autowired
    private ISysCageMouseService sysCageMouseService;

    @RequiresPermissions("system:SysCageMouse:view")
    @GetMapping()
    public String SysCageMouse()
    {
        return prefix + "/SysCageMouse";
    }

    /**
     * 查询笼子-小鼠关系列表
     */
    @RequiresPermissions("system:SysCageMouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCageMouse sysCageMouse)
    {
        startPage();
        List<SysCageMouse> list = sysCageMouseService.selectSysCageMouseList(sysCageMouse);
        return getDataTable(list);
    }

    /**
     * 导出笼子-小鼠关系列表
     */
    @RequiresPermissions("system:SysCageMouse:export")
    @Log(title = "笼子-小鼠关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCageMouse sysCageMouse)
    {
        List<SysCageMouse> list = sysCageMouseService.selectSysCageMouseList(sysCageMouse);
        ExcelUtil<SysCageMouse> util = new ExcelUtil<SysCageMouse>(SysCageMouse.class);
        return util.exportExcel(list, "笼子-小鼠关系数据");
    }

    /**
     * 新增笼子-小鼠关系
     */
    @RequiresPermissions("system:SysCageMouse:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存笼子-小鼠关系
     */
    @RequiresPermissions("system:SysCageMouse:add")
    @Log(title = "笼子-小鼠关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCageMouse sysCageMouse)
    {
        return toAjax(sysCageMouseService.insertSysCageMouse(sysCageMouse));
    }

    /**
     * 修改笼子-小鼠关系
     */
    @RequiresPermissions("system:SysCageMouse:edit")
    @GetMapping("/edit/{cageMouseId}")
    public String edit(@PathVariable("cageMouseId") Long cageMouseId, ModelMap mmap)
    {
        SysCageMouse sysCageMouse = sysCageMouseService.selectSysCageMouseByCageMouseId(cageMouseId);
        mmap.put("sysCageMouse", sysCageMouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存笼子-小鼠关系
     */
    @RequiresPermissions("system:SysCageMouse:edit")
    @Log(title = "笼子-小鼠关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCageMouse sysCageMouse)
    {
        return toAjax(sysCageMouseService.updateSysCageMouse(sysCageMouse));
    }

    /**
     * 删除笼子-小鼠关系
     */
    @RequiresPermissions("system:SysCageMouse:remove")
    @Log(title = "笼子-小鼠关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCageMouseService.deleteSysCageMouseByCageMouseIds(ids));
    }
}
