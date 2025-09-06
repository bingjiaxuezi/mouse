package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TExperiment;
import com.ruoyi.system.domain.dto.TExperimentDTO;
import com.ruoyi.system.domain.vo.TExperimentVO;
import com.ruoyi.system.service.ITExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 实验Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@RestController
@RequestMapping("/system/experiment")
public class TExperimentController extends BaseController {
    
    @Autowired
    private ITExperimentService tExperimentService;

    /**
     * 查询实验列表
     */
    @RequiresPermissions("system:experiment:list")
    @GetMapping("/list")
    public TableDataInfo list(TExperiment tExperiment) {
        startPage();
        List<TExperimentVO> list = tExperimentService.selectTExperimentList(tExperiment);
        return getDataTable(list);
    }

    /**
     * 导出实验列表
     */
    @RequiresPermissions("system:experiment:export")
    @Log(title = "实验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TExperiment tExperiment) {
        List<TExperimentVO> list = tExperimentService.selectTExperimentList(tExperiment);
        ExcelUtil<TExperimentVO> util = new ExcelUtil<TExperimentVO>(TExperimentVO.class);
        return util.exportExcel(list, "实验数据");
    }

    /**
     * 获取实验详细信息
     */
    @RequiresPermissions("system:experiment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tExperimentService.selectTExperimentById(id));
    }

    /**
     * 新增实验
     */
    @RequiresPermissions("system:experiment:add")
    @Log(title = "实验", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TExperimentDTO tExperimentDTO) {
        if ("1".equals(tExperimentService.checkExperimentCodeUnique(tExperimentDTO.getExperimentCode()))) {
            return AjaxResult.error("新增实验'" + tExperimentDTO.getExperimentName() + "'失败，实验编号已存在");
        }
        return toAjax(tExperimentService.insertTExperiment(tExperimentDTO));
    }

    /**
     * 修改实验
     */
    @RequiresPermissions("system:experiment:edit")
    @Log(title = "实验", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody TExperimentDTO tExperimentDTO) {
        return toAjax(tExperimentService.updateTExperiment(tExperimentDTO));
    }

    /**
     * 保存实验（新增或修改）
     */
    @PostMapping
    public AjaxResult save(@Validated @RequestBody TExperimentDTO tExperimentDTO) {
        if (tExperimentDTO.getId() != null) {
            // 修改操作
            return toAjax(tExperimentService.updateTExperiment(tExperimentDTO));
        } else {
            // 新增操作
            if ("1".equals(tExperimentService.checkExperimentCodeUnique(tExperimentDTO.getExperimentCode()))) {
                return AjaxResult.error("新增实验'" + tExperimentDTO.getExperimentName() + "'失败，实验编号已存在");
            }
            return toAjax(tExperimentService.insertTExperiment(tExperimentDTO));
        }
    }

    /**
     * 删除实验
     */
    @RequiresPermissions("system:experiment:remove")
    @Log(title = "实验", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(tExperimentService.deleteTExperimentByIds(ids));
    }
}