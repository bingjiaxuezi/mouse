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
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;

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
        try {
            return toAjax(sysMouseService.insertSysMouse(sysMouse));
        } catch (Exception e) {
            return handleDatabaseException(e);
        }
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
        try {
            return toAjax(sysMouseService.updateSysMouse(sysMouse));
        } catch (Exception e) {
            return handleDatabaseException(e);
        }
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
    
    /**
     * 查看小鼠详情
     */
    @RequiresPermissions("system:SysMouse:view")
    @GetMapping("/detail/{mouseId}")
    public String detail(@PathVariable("mouseId") Long mouseId, ModelMap mmap)
    {
        SysMouse sysMouse = sysMouseService.selectSysMouseByMouseId(mouseId);
        mmap.put("sysMouse", sysMouse);
        return prefix + "/detail";
    }
    
    /**
     * 处理数据库异常，提供友好的错误信息
     */
    private AjaxResult handleDatabaseException(Exception e) {
        String errorMessage = e.getMessage();
        
        // 处理RFID标签号重复
        if (errorMessage.contains("uk_rfid_tag")) {
            if (errorMessage.contains("Duplicate entry ''")) {
                return AjaxResult.error("RFID标签号不能为空，请填写唯一的RFID标签号");
            } else {
                return AjaxResult.error("RFID标签号已存在，请使用不同的RFID标签号");
            }
        }
        
        // 处理耳标号重复
        if (errorMessage.contains("uk_ear_tag")) {
            if (errorMessage.contains("Duplicate entry ''")) {
                return AjaxResult.error("耳标号不能为空，请填写唯一的耳标号");
            } else {
                return AjaxResult.error("耳标号已存在，请使用不同的耳标号");
            }
        }
        
        // 处理小鼠编号重复
        if (errorMessage.contains("uk_mouse_code")) {
            return AjaxResult.error("小鼠编号已存在，请使用不同的编号");
        }
        
        // 处理其他唯一约束冲突
        if (errorMessage.contains("Duplicate entry")) {
            return AjaxResult.error("数据重复，请检查RFID标签号、耳标号等字段是否唯一");
        }
        
        // 处理其他数据库约束异常
        if (e instanceof SQLIntegrityConstraintViolationException || e instanceof DuplicateKeyException) {
            return AjaxResult.error("数据约束冲突，请检查输入的数据是否符合要求");
        }
        
        // 默认错误信息
        return AjaxResult.error("操作失败：" + e.getMessage());
    }
}
