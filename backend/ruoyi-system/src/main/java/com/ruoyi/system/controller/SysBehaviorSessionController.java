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
import com.ruoyi.system.domain.SysBehaviorSession;
import com.ruoyi.system.service.ISysBehaviorSessionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小鼠行为监测会话Controller
 * 
 * @author lczj
 * @date 2025-09-21
 */
@Controller
@RequestMapping("/system/SysBehaviorSession")
public class SysBehaviorSessionController extends BaseController
{
    private String prefix = "system/SysBehaviorSession";

    @Autowired
    private ISysBehaviorSessionService sysBehaviorSessionService;

    @RequiresPermissions("system:SysBehaviorSession:view")
    @GetMapping()
    public String SysBehaviorSession()
    {
        return prefix + "/SysBehaviorSession";
    }

    /**
     * 查询小鼠行为监测会话列表
     */
    @RequiresPermissions("system:SysBehaviorSession:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBehaviorSession sysBehaviorSession)
    {
        startPage();
        List<SysBehaviorSession> list = sysBehaviorSessionService.selectSysBehaviorSessionList(sysBehaviorSession);
        return getDataTable(list);
    }

    /**
     * 导出小鼠行为监测会话列表
     */
    @RequiresPermissions("system:SysBehaviorSession:export")
    @Log(title = "小鼠行为监测会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBehaviorSession sysBehaviorSession)
    {
        List<SysBehaviorSession> list = sysBehaviorSessionService.selectSysBehaviorSessionList(sysBehaviorSession);
        ExcelUtil<SysBehaviorSession> util = new ExcelUtil<SysBehaviorSession>(SysBehaviorSession.class);
        return util.exportExcel(list, "小鼠行为监测会话数据");
    }

    /**
     * 新增小鼠行为监测会话
     */
    @RequiresPermissions("system:SysBehaviorSession:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小鼠行为监测会话
     */
    @RequiresPermissions("system:SysBehaviorSession:add")
    @Log(title = "小鼠行为监测会话", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBehaviorSession sysBehaviorSession)
    {
        return toAjax(sysBehaviorSessionService.insertSysBehaviorSession(sysBehaviorSession));
    }

    /**
     * 修改小鼠行为监测会话
     */
    @RequiresPermissions("system:SysBehaviorSession:edit")
    @GetMapping("/edit/{sessionId}")
    public String edit(@PathVariable("sessionId") Long sessionId, ModelMap mmap)
    {
        SysBehaviorSession sysBehaviorSession = sysBehaviorSessionService.selectSysBehaviorSessionBySessionId(sessionId);
        mmap.put("sysBehaviorSession", sysBehaviorSession);
        return prefix + "/edit";
    }

    /**
     * 修改保存小鼠行为监测会话
     */
    @RequiresPermissions("system:SysBehaviorSession:edit")
    @Log(title = "小鼠行为监测会话", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBehaviorSession sysBehaviorSession)
    {
        return toAjax(sysBehaviorSessionService.updateSysBehaviorSession(sysBehaviorSession));
    }

    /**
     * 删除小鼠行为监测会话
     */
    @RequiresPermissions("system:SysBehaviorSession:remove")
    @Log(title = "小鼠行为监测会话", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBehaviorSessionService.deleteSysBehaviorSessionBySessionIds(ids));
    }
}
