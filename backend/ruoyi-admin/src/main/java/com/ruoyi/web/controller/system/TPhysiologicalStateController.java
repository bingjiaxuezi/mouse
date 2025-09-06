package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TPhysiologicalState;
import com.ruoyi.system.domain.dto.TPhysiologicalStateDTO;
import com.ruoyi.system.domain.vo.TPhysiologicalStateVO;
import com.ruoyi.system.service.ITPhysiologicalStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 生理状态Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@RestController
@RequestMapping("/system/physiologicalstate")
public class TPhysiologicalStateController extends BaseController {
    
    @Autowired
    private ITPhysiologicalStateService tPhysiologicalStateService;

    /**
     * 查询生理状态列表
     */
    @RequiresPermissions("system:physiologicalstate:list")
    @GetMapping("/list")
    public TableDataInfo list(TPhysiologicalState tPhysiologicalState) {
        startPage();
        List<TPhysiologicalStateVO> list = tPhysiologicalStateService.selectTPhysiologicalStateList(tPhysiologicalState);
        return getDataTable(list);
    }

    /**
     * 导出生理状态列表
     */
    @RequiresPermissions("system:physiologicalstate:export")
    @Log(title = "生理状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPhysiologicalState tPhysiologicalState) {
        List<TPhysiologicalStateVO> list = tPhysiologicalStateService.selectTPhysiologicalStateList(tPhysiologicalState);
        ExcelUtil<TPhysiologicalStateVO> util = new ExcelUtil<TPhysiologicalStateVO>(TPhysiologicalStateVO.class);
        util.exportExcel(response, list, "生理状态数据");
    }

    /**
     * 获取生理状态详细信息
     */
    @RequiresPermissions("system:physiologicalstate:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tPhysiologicalStateService.selectTPhysiologicalStateById(id));
    }

    /**
     * 新增生理状态
     */
    @RequiresPermissions("system:physiologicalstate:add")
    @Log(title = "生理状态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TPhysiologicalStateDTO tPhysiologicalStateDTO) {
        return toAjax(tPhysiologicalStateService.insertTPhysiologicalState(tPhysiologicalStateDTO));
    }

    /**
     * 修改生理状态
     */
    @RequiresPermissions("system:physiologicalstate:edit")
    @Log(title = "生理状态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody TPhysiologicalStateDTO tPhysiologicalStateDTO) {
        return toAjax(tPhysiologicalStateService.updateTPhysiologicalState(tPhysiologicalStateDTO));
    }

    /**
     * 保存生理状态
     */
    @PostMapping
    public AjaxResult save(@Validated @RequestBody TPhysiologicalStateDTO tPhysiologicalStateDTO) {
        if (tPhysiologicalStateDTO.getId() != null) {
            return toAjax(tPhysiologicalStateService.updateTPhysiologicalState(tPhysiologicalStateDTO));
        } else {
            return toAjax(tPhysiologicalStateService.insertTPhysiologicalState(tPhysiologicalStateDTO));
        }
    }

    /**
     * 删除生理状态
     */
    @RequiresPermissions("system:physiologicalstate:remove")
    @Log(title = "生理状态", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(tPhysiologicalStateService.deleteTPhysiologicalStateByIds(ids));
    }
}