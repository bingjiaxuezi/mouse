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
import com.ruoyi.system.domain.SysMouse;
import com.ruoyi.system.service.ISysMouseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小鼠基础信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-13
 */
@Controller
@RequestMapping("/system/SysMouse")
public class SysMouseController extends BaseController
{
    private String prefix = "system/SysMouse";

    @Autowired
    private ISysMouseService sysMouseService;

    @RequiresPermissions("system:SysMouse:view")
    @GetMapping()
    public String SysMouse()
    {
        return prefix + "/SysMouse";
    }

    /**
     * 查询小鼠基础信息列表
     */
    @RequiresPermissions("system:SysMouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysMouse sysMouse)
    {
        startPage();
        List<SysMouse> list = sysMouseService.selectSysMouseList(sysMouse);
        return getDataTable(list);
    }

    /**
     * 导出小鼠基础信息列表
     */
    @RequiresPermissions("system:SysMouse:export")
    @Log(title = "小鼠基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysMouse sysMouse)
    {
        List<SysMouse> list = sysMouseService.selectSysMouseList(sysMouse);
        ExcelUtil<SysMouse> util = new ExcelUtil<SysMouse>(SysMouse.class);
        return util.exportExcel(list, "小鼠基础信息数据");
    }

    /**
     * 新增小鼠基础信息
     */
    @RequiresPermissions("system:SysMouse:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小鼠基础信息
     */
    @RequiresPermissions("system:SysMouse:add")
    @Log(title = "小鼠基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysMouse sysMouse)
    {
        return toAjax(sysMouseService.insertSysMouse(sysMouse));
    }

    /**
     * 修改小鼠基础信息
     */
    @RequiresPermissions("system:SysMouse:edit")
    @GetMapping("/edit/{mouseId}")
    public String edit(@PathVariable("mouseId") Long mouseId, ModelMap mmap)
    {
        SysMouse sysMouse = sysMouseService.selectSysMouseByMouseId(mouseId);
        mmap.put("sysMouse", sysMouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存小鼠基础信息
     */
    @RequiresPermissions("system:SysMouse:edit")
    @Log(title = "小鼠基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysMouse sysMouse)
    {
        return toAjax(sysMouseService.updateSysMouse(sysMouse));
    }

    /**
     * 删除小鼠基础信息
     */
    @RequiresPermissions("system:SysMouse:remove")
    @Log(title = "小鼠基础信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysMouseService.deleteSysMouseByMouseIds(ids));
    }
}
