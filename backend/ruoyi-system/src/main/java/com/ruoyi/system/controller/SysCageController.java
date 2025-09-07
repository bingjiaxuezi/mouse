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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysCage;
import com.ruoyi.system.service.ISysCageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 笼子管理Controller
 * 
 * @author lczj
 * @date 2025-09-07
 */
@Controller
@RequestMapping("/system/SysCage")
public class SysCageController extends BaseController
{
    private String prefix = "system/SysCage";

    @Autowired
    private ISysCageService sysCageService;

    @RequiresPermissions("system:SysCage:view")
    @GetMapping()
    public String SysCage()
    {
        return prefix + "/SysCage";
    }

    /**
     * 查询笼子管理列表
     */
    @RequiresPermissions("system:SysCage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCage sysCage)
    {
        startPage();
        List<SysCage> list = sysCageService.selectSysCageList(sysCage);
        return getDataTable(list);
    }

    /**
     * 导出笼子管理列表
     */
    @RequiresPermissions("system:SysCage:export")
    @Log(title = "笼子管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCage sysCage)
    {
        List<SysCage> list = sysCageService.selectSysCageList(sysCage);
        ExcelUtil<SysCage> util = new ExcelUtil<SysCage>(SysCage.class);
        return util.exportExcel(list, "笼子管理数据");
    }

    /**
     * 新增笼子管理
     */
    @RequiresPermissions("system:SysCage:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存笼子管理
     */
    @RequiresPermissions("system:SysCage:add")
    @Log(title = "笼子管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCage sysCage)
    {
        return toAjax(sysCageService.insertSysCage(sysCage));
    }

    /**
     * 修改笼子管理
     */
    @RequiresPermissions("system:SysCage:edit")
    @GetMapping("/edit/{cageId}")
    public String edit(@PathVariable("cageId") Long cageId, ModelMap mmap)
    {
        SysCage sysCage = sysCageService.selectSysCageByCageId(cageId);
        mmap.put("sysCage", sysCage);
        return prefix + "/edit";
    }

    /**
     * 修改保存笼子管理
     */
    @RequiresPermissions("system:SysCage:edit")
    @Log(title = "笼子管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCage sysCage)
    {
        return toAjax(sysCageService.updateSysCage(sysCage));
    }

    /**
     * 批量更新笼子状态
     */
    @RequiresPermissions("system:SysCage:edit")
    @Log(title = "笼子管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam("cageIds") String cageIds, @RequestParam("status") String status)
    {
        try {
            return toAjax(sysCageService.updateCageStatus(cageIds, status));
        } catch (Exception e) {
            return AjaxResult.error("批量更新笼子状态失败: " + e.getMessage());
        }
    }

    /**
     * 删除笼子管理
     */
    @RequiresPermissions("system:SysCage:remove")
    @Log(title = "笼子管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCageService.deleteSysCageByCageIds(ids));
    }
}
