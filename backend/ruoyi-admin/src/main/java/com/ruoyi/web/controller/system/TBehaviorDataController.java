package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TBehaviorData;
import com.ruoyi.system.domain.dto.TBehaviorDataDTO;
import com.ruoyi.system.domain.vo.TBehaviorDataVO;
import com.ruoyi.system.service.ITBehaviorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 行为数据Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@RestController
@RequestMapping("/system/behaviordata")
public class TBehaviorDataController extends BaseController {
    
    @Autowired
    private ITBehaviorDataService tBehaviorDataService;

    /**
     * 查询行为数据列表
     */
    @RequiresPermissions("system:behaviordata:list")
    @GetMapping("/list")
    public TableDataInfo list(TBehaviorData tBehaviorData) {
        // 清理空的时间参数，避免SQL错误
        Map<String, Object> params = tBehaviorData.getParams();
        if (params != null) {
            Object beginTimestamp = params.get("beginTimestamp");
            Object endTimestamp = params.get("endTimestamp");
            
            System.out.println("DEBUG: beginTimestamp = [" + beginTimestamp + "], type = " + (beginTimestamp != null ? beginTimestamp.getClass().getName() : "null"));
            System.out.println("DEBUG: endTimestamp = [" + endTimestamp + "], type = " + (endTimestamp != null ? endTimestamp.getClass().getName() : "null"));
            
            // 如果时间参数为空字符串或"??",则移除该参数
            if (beginTimestamp != null && ("".equals(beginTimestamp.toString().trim()) || "??".equals(beginTimestamp.toString().trim()))) {
                System.out.println("DEBUG: Removing beginTimestamp");
                params.remove("beginTimestamp");
            }
            if (endTimestamp != null && ("".equals(endTimestamp.toString().trim()) || "??".equals(endTimestamp.toString().trim()))) {
                System.out.println("DEBUG: Removing endTimestamp");
                params.remove("endTimestamp");
            }
            
            System.out.println("DEBUG: Final params = " + params);
        }
        
        startPage();
        List<TBehaviorDataVO> list = tBehaviorDataService.selectTBehaviorDataList(tBehaviorData);
        return getDataTable(list);
    }

    /**
     * 导出行为数据列表
     */
    @RequiresPermissions("system:behaviordata:export")
    @Log(title = "行为数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TBehaviorData tBehaviorData) {
        List<TBehaviorDataVO> list = tBehaviorDataService.selectTBehaviorDataList(tBehaviorData);
        ExcelUtil<TBehaviorDataVO> util = new ExcelUtil<TBehaviorDataVO>(TBehaviorDataVO.class);
        util.exportExcel(response, list, "行为数据");
    }

    /**
     * 获取行为数据详细信息
     */
    @RequiresPermissions("system:behaviordata:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tBehaviorDataService.selectTBehaviorDataById(id));
    }

    /**
     * 新增行为数据
     */
    @RequiresPermissions("system:behaviordata:add")
    @Log(title = "行为数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TBehaviorDataDTO tBehaviorDataDTO) {
        return toAjax(tBehaviorDataService.insertTBehaviorData(tBehaviorDataDTO));
    }

    /**
     * 修改行为数据
     */
    @RequiresPermissions("system:behaviordata:edit")
    @Log(title = "行为数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody TBehaviorDataDTO tBehaviorDataDTO) {
        return toAjax(tBehaviorDataService.updateTBehaviorData(tBehaviorDataDTO));
    }

    /**
     * 保存行为数据（新增或修改）
     */
    @PostMapping
    public AjaxResult save(@Validated @RequestBody TBehaviorDataDTO tBehaviorDataDTO) {
        if (tBehaviorDataDTO.getId() != null) {
            // 修改操作
            return toAjax(tBehaviorDataService.updateTBehaviorData(tBehaviorDataDTO));
        } else {
            // 新增操作
            return toAjax(tBehaviorDataService.insertTBehaviorData(tBehaviorDataDTO));
        }
    }

    /**
     * 删除行为数据
     */
    @RequiresPermissions("system:behaviordata:remove")
    @Log(title = "行为数据", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(tBehaviorDataService.deleteTBehaviorDataByIds(ids));
    }
}