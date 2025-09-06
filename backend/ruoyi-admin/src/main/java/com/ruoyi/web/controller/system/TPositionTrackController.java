package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TPositionTrack;
import com.ruoyi.system.domain.dto.TPositionTrackDTO;
import com.ruoyi.system.domain.vo.TPositionTrackVO;
import com.ruoyi.system.service.ITPositionTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 位置跟踪Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@RestController
@RequestMapping("/system/positiontrack")
public class TPositionTrackController extends BaseController {
    
    @Autowired
    private ITPositionTrackService tPositionTrackService;

    /**
     * 查询位置跟踪列表
     */
    @RequiresPermissions("system:positiontrack:list")
    @GetMapping("/list")
    public TableDataInfo list(TPositionTrack tPositionTrack) {
        startPage();
        List<TPositionTrackVO> list = tPositionTrackService.selectTPositionTrackList(tPositionTrack);
        return getDataTable(list);
    }

    /**
     * 导出位置跟踪列表
     */
    @RequiresPermissions("system:positiontrack:export")
    @Log(title = "位置跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPositionTrack tPositionTrack) {
        List<TPositionTrackVO> list = tPositionTrackService.selectTPositionTrackList(tPositionTrack);
        ExcelUtil<TPositionTrackVO> util = new ExcelUtil<TPositionTrackVO>(TPositionTrackVO.class);
        util.exportExcel(response, list, "位置跟踪数据");
    }

    /**
     * 获取位置跟踪详细信息
     */
    @RequiresPermissions("system:positiontrack:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tPositionTrackService.selectTPositionTrackById(id));
    }

    /**
     * 新增位置跟踪
     */
    @RequiresPermissions("system:positiontrack:add")
    @Log(title = "位置跟踪", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TPositionTrackDTO tPositionTrackDTO) {
        return toAjax(tPositionTrackService.insertTPositionTrack(tPositionTrackDTO));
    }

    /**
     * 修改位置跟踪
     */
    @RequiresPermissions("system:positiontrack:edit")
    @Log(title = "位置跟踪", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody TPositionTrackDTO tPositionTrackDTO) {
        return toAjax(tPositionTrackService.updateTPositionTrack(tPositionTrackDTO));
    }

    /**
     * 保存位置跟踪
     */
    @PostMapping
    public AjaxResult save(@Validated @RequestBody TPositionTrackDTO tPositionTrackDTO) {
        if (tPositionTrackDTO.getId() != null) {
            return toAjax(tPositionTrackService.updateTPositionTrack(tPositionTrackDTO));
        } else {
            return toAjax(tPositionTrackService.insertTPositionTrack(tPositionTrackDTO));
        }
    }

    /**
     * 删除位置跟踪
     */
    @RequiresPermissions("system:positiontrack:remove")
    @Log(title = "位置跟踪", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(tPositionTrackService.deleteTPositionTrackByIds(ids));
    }
}