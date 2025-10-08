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
import com.ruoyi.system.domain.SysBehaviorEvent;
import com.ruoyi.system.service.ISysBehaviorEventService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小鼠行为事件核心（记录具体行为事件）Controller
 * 
 * @author lczj
 * @date 2025-09-27
 */
@Controller
@RequestMapping("/system/SysBehaviorEvent")
public class SysBehaviorEventController extends BaseController
{
    private String prefix = "system/SysBehaviorEvent";

    @Autowired
    private ISysBehaviorEventService sysBehaviorEventService;

    @RequiresPermissions("system:SysBehaviorEvent:view")
    @GetMapping()
    public String SysBehaviorEvent()
    {
        return prefix + "/SysBehaviorEvent";
    }

    /**
     * 查询小鼠行为事件核心（记录具体行为事件）列表
     */
    @RequiresPermissions("system:SysBehaviorEvent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBehaviorEvent sysBehaviorEvent)
    {
        startPage();
        List<SysBehaviorEvent> list = sysBehaviorEventService.selectSysBehaviorEventList(sysBehaviorEvent);
        return getDataTable(list);
    }

    /**
     * 导出小鼠行为事件核心（记录具体行为事件）列表
     */
    @RequiresPermissions("system:SysBehaviorEvent:export")
    @Log(title = "小鼠行为事件核心（记录具体行为事件）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBehaviorEvent sysBehaviorEvent)
    {
        List<SysBehaviorEvent> list = sysBehaviorEventService.selectSysBehaviorEventList(sysBehaviorEvent);
        ExcelUtil<SysBehaviorEvent> util = new ExcelUtil<SysBehaviorEvent>(SysBehaviorEvent.class);
        return util.exportExcel(list, "小鼠行为事件核心（记录具体行为事件）数据");
    }

    /**
     * 新增小鼠行为事件核心（记录具体行为事件）
     */
    @RequiresPermissions("system:SysBehaviorEvent:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小鼠行为事件核心（记录具体行为事件）
     */
    @RequiresPermissions("system:SysBehaviorEvent:add")
    @Log(title = "小鼠行为事件核心（记录具体行为事件）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBehaviorEvent sysBehaviorEvent)
    {
        return toAjax(sysBehaviorEventService.insertSysBehaviorEvent(sysBehaviorEvent));
    }

    /**
     * 修改小鼠行为事件核心（记录具体行为事件）
     */
    @RequiresPermissions("system:SysBehaviorEvent:edit")
    @GetMapping("/edit/{eventId}")
    public String edit(@PathVariable("eventId") Long eventId, ModelMap mmap)
    {
        SysBehaviorEvent sysBehaviorEvent = sysBehaviorEventService.selectSysBehaviorEventByEventId(eventId);
        mmap.put("sysBehaviorEvent", sysBehaviorEvent);
        return prefix + "/edit";
    }

    /**
     * 修改保存小鼠行为事件核心（记录具体行为事件）
     */
    @RequiresPermissions("system:SysBehaviorEvent:edit")
    @Log(title = "小鼠行为事件核心（记录具体行为事件）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBehaviorEvent sysBehaviorEvent)
    {
        return toAjax(sysBehaviorEventService.updateSysBehaviorEvent(sysBehaviorEvent));
    }

    /**
     * 删除小鼠行为事件核心（记录具体行为事件）
     */
    @RequiresPermissions("system:SysBehaviorEvent:remove")
    @Log(title = "小鼠行为事件核心（记录具体行为事件）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBehaviorEventService.deleteSysBehaviorEventByEventIds(ids));
    }
}
