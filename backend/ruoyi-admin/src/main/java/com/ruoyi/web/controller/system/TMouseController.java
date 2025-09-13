package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.TMouse;
import com.ruoyi.system.domain.dto.TMouseDTO;
import com.ruoyi.system.domain.vo.TMouseVO;
import com.ruoyi.system.service.ITMouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 老鼠Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Controller
@RequestMapping("/system/mouse")
public class TMouseController extends BaseController {
    
    private static final Logger logger = LoggerFactory.getLogger(TMouseController.class);
    
    private String prefix = "system/mouse";
    
    @Autowired
    private ITMouseService tMouseService;

    /**
     * 小鼠管理页面
     */
    @RequiresPermissions("system:mouse:view")
    @GetMapping()
    public String mouse() {
        return prefix + "/mouse";
    }
    
    /**
     * 新增小鼠页面
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }
    




    /**
     * 修改小鼠页面
     */
    @RequiresPermissions("system:mouse:view")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TMouseVO tMouse = tMouseService.selectTMouseById(id);
        mmap.put("tMouse", tMouse);
        return prefix + "/edit";
    }

    /**
     * 查询老鼠列表
     */
    @RequiresPermissions("system:mouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TMouse tMouse) {
        startPage();
        List<TMouseVO> list = tMouseService.selectTMouseList(tMouse);
        return getDataTable(list);
    }

    @GetMapping("/test")
    public String test(){
        return "FUCK MOUSE1505";
    }

    /**
     * 导出老鼠列表
     */
    @RequiresPermissions("system:mouse:export")
    @Log(title = "老鼠", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TMouse tMouse) {
        List<TMouseVO> list = tMouseService.selectTMouseList(tMouse);
        ExcelUtil<TMouseVO> util = new ExcelUtil<TMouseVO>(TMouseVO.class);
        return util.exportExcel(list, "老鼠数据");
    }

    /**
     * 查询老鼠详细
     */
    @RequiresPermissions("system:mouse:view")
    @GetMapping(value = "/{idOrCode}")
    public String getById(@PathVariable("idOrCode") String idOrCode, ModelMap mmap) {
        TMouseVO tMouse = null;
        try {
            // 尝试解析为Long类型的ID
            Long id = Long.parseLong(idOrCode);
            tMouse = tMouseService.selectTMouseById(id);
        } catch (NumberFormatException e) {
            // 如果不是数字，按mouseCode查询
            tMouse = tMouseService.selectTMouseByMouseCode(idOrCode);
        }
        mmap.put("tMouse", tMouse);
        return prefix + "/edit";
    }

    /**
     * 根据ID或mouseCode获取老鼠信息（API接口）
     */
    @Anonymous
    @GetMapping(value = "/api/{idOrCode}")
    @ResponseBody
    public AjaxResult getMouseInfo(@PathVariable("idOrCode") String idOrCode) {
        try {
            // 尝试解析为Long类型的ID
            Long id = Long.parseLong(idOrCode);
            TMouseVO tMouse = tMouseService.selectTMouseById(id);
            if (tMouse != null) {
                return AjaxResult.success(tMouse);
            }
        } catch (NumberFormatException e) {
            // 如果不是数字，按mouseCode查询
        }
        
        // 按mouseCode查询
        TMouseVO tMouse = tMouseService.selectTMouseByMouseCode(idOrCode);
        if (tMouse != null) {
            return AjaxResult.success(tMouse);
        }
        
        return AjaxResult.error("未找到对应的老鼠信息");
    }

    /**
     * 新增老鼠
     */
    @RequiresPermissions("system:mouse:add")
    @Log(title = "老鼠", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    @ResponseBody
    public AjaxResult add(TMouseDTO tMouseDTO) {
        if ("1".equals(tMouseService.checkMouseCodeUnique(tMouseDTO.getMouseCode()))) {
            return AjaxResult.error("新增老鼠'" + tMouseDTO.getMouseName() + "'失败，老鼠编号已存在");
        }
        return toAjax(tMouseService.insertTMouse(tMouseDTO));
    }

    /**
     * 修改老鼠
     */
    @RequiresPermissions("system:mouse:edit")
    @Log(title = "老鼠", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/edit")
    @ResponseBody
    public AjaxResult edit(TMouseDTO tMouseDTO) {
        return toAjax(tMouseService.updateTMouse(tMouseDTO));
    }

    /**
     * 保存老鼠（新增或修改）
     */
    @PostMapping
    @ResponseBody
    public AjaxResult save(TMouseDTO tMouseDTO) {
        try {
            if (tMouseDTO.getId() != null) {
                // 修改操作
                return toAjax(tMouseService.updateTMouse(tMouseDTO));
            } else {
                // 新增操作 - 只有在填写了编号时才检查重复
                if (tMouseDTO.getMouseCode() != null && !tMouseDTO.getMouseCode().trim().isEmpty()) {
                    if ("1".equals(tMouseService.checkMouseCodeUnique(tMouseDTO.getMouseCode()))) {
                        return AjaxResult.error("新增小鼠失败：编号 '" + tMouseDTO.getMouseCode() + "' 已存在，请使用其他编号或留空自动生成");
                    }
                }
                
                // 验证必填字段
                if (tMouseDTO.getMouseName() == null || tMouseDTO.getMouseName().trim().isEmpty()) {
                    return AjaxResult.error("新增小鼠失败：小鼠名称不能为空");
                }
                if (tMouseDTO.getGender() == null || tMouseDTO.getGender().trim().isEmpty()) {
                    return AjaxResult.error("新增小鼠失败：请选择小鼠性别");
                }
                
                return toAjax(tMouseService.insertTMouse(tMouseDTO));
            }
        } catch (Exception e) {
            logger.error("保存小鼠信息失败", e);
            String errorMsg = e.getMessage();
            if (errorMsg != null) {
                if (errorMsg.contains("Field 'gender' doesn't have a default value")) {
                    return AjaxResult.error("保存失败：请选择小鼠性别");
                } else if (errorMsg.contains("Duplicate entry")) {
                    return AjaxResult.error("保存失败：小鼠编号已存在，请使用其他编号");
                } else if (errorMsg.contains("cannot be null")) {
                    return AjaxResult.error("保存失败：请填写完整的必填信息");
                }
            }
            return AjaxResult.error("保存小鼠信息失败，请检查输入信息是否完整");
        }
    }

    /**
     * 删除老鼠
     */
    @RequiresPermissions("system:mouse:remove")
    @Log(title = "老鼠", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult remove(String ids) {
        Long[] mouseIds = Convert.toLongArray(ids);
        return toAjax(tMouseService.deleteTMouseByIds(mouseIds));
    }
    
    /**
     * 状态修改
     */
    @RequiresPermissions("system:mouse:edit")
    @Log(title = "老鼠管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Long id, Integer status) {
        TMouseDTO tMouseDTO = new TMouseDTO();
        tMouseDTO.setId(id);
        tMouseDTO.setStatus(status);
        return toAjax(tMouseService.updateTMouse(tMouseDTO));
    }
}