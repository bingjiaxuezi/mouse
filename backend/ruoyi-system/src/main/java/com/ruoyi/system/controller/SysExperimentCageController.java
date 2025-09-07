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
import com.ruoyi.system.domain.SysExperimentCage;
import com.ruoyi.system.domain.vo.SysExperimentCageVO;
import com.ruoyi.system.service.ISysExperimentCageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实验笼子关系Controller
 * 
 * @author ruoyi
 * @date 2025-09-07
 */
@Controller
@RequestMapping("/system/SysExperimentCage")
public class SysExperimentCageController extends BaseController
{
    private String prefix = "system/SysExperimentCage";

    @Autowired
    private ISysExperimentCageService sysExperimentCageService;

    @RequiresPermissions("system:SysExperimentCage:view")
    @GetMapping()
    public String SysExperimentCage()
    {
        return prefix + "/SysExperimentCage";
    }

    /**
     * 查询实验笼子关系列表
     */
    @RequiresPermissions("system:SysExperimentCage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysExperimentCage sysExperimentCage)
    {
        startPage();
        List<SysExperimentCage> list = sysExperimentCageService.selectSysExperimentCageList(sysExperimentCage);
        return getDataTable(list);
    }

    /**
     * 导出实验笼子关系列表
     */
    @RequiresPermissions("system:SysExperimentCage:export")
    @Log(title = "实验笼子关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysExperimentCage sysExperimentCage)
    {
        List<SysExperimentCage> list = sysExperimentCageService.selectSysExperimentCageList(sysExperimentCage);
        ExcelUtil<SysExperimentCage> util = new ExcelUtil<SysExperimentCage>(SysExperimentCage.class);
        return util.exportExcel(list, "实验笼子关系数据");
    }

    /**
     * 新增实验笼子关系
     */
    @RequiresPermissions("system:SysExperimentCage:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实验笼子关系
     */
    @RequiresPermissions("system:SysExperimentCage:add")
    @Log(title = "实验笼子关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysExperimentCage sysExperimentCage)
    {
        return toAjax(sysExperimentCageService.insertSysExperimentCage(sysExperimentCage));
    }

    /**
     * 修改实验笼子关系
     */
    @RequiresPermissions("system:SysExperimentCage:edit")
    @GetMapping("/edit/{relationId}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("relationId") Long relationId)
    {
        SysExperimentCage sysExperimentCage = sysExperimentCageService.selectSysExperimentCageByRelationId(relationId);
        return AjaxResult.success(sysExperimentCage);
    }
    

        // @RequiresPermissions("system:SysExperimentCage:edit")
    @GetMapping("/edit1/{relationId}")
    public String edit1(@PathVariable("relationId") Long relationId, ModelMap mmap)
    {
        SysExperimentCage sysExperimentCage = sysExperimentCageService.selectSysExperimentCageByRelationId(relationId);
        mmap.put("sysExperimentCage", sysExperimentCage);
        return prefix + "/edit1";
    }


    /**
     * 修改保存实验笼子关系
     */
    @RequiresPermissions("system:SysExperimentCage:edit")
    @Log(title = "实验笼子关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysExperimentCage sysExperimentCage)
    {
        return toAjax(sysExperimentCageService.updateSysExperimentCage(sysExperimentCage));
    }

    /**
     * 删除实验笼子关系
     */
    @RequiresPermissions("system:SysExperimentCage:remove")
    @Log(title = "实验笼子关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysExperimentCageService.deleteSysExperimentCageByRelationIds(ids));
    }

    /**
     * 根据实验ID查询笼子绑定关系列表
     */
    @RequiresPermissions("system:SysExperimentCage:list")
    @PostMapping("/listByExperimentId")
    @ResponseBody
    public TableDataInfo listByExperimentId(Long experimentId)
    {
        startPage();
        SysExperimentCage sysExperimentCage = new SysExperimentCage();
        sysExperimentCage.setExperimentId(experimentId);
        List<SysExperimentCageVO> list = sysExperimentCageService.selectSysExperimentCageVOList(sysExperimentCage);
        return getDataTable(list);
    }
}
