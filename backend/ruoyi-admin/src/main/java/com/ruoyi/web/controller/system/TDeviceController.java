package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TDevice;
import com.ruoyi.system.domain.dto.TDeviceDTO;
import com.ruoyi.system.domain.vo.TDeviceVO;
import com.ruoyi.system.service.ITDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@RestController
@RequestMapping("/system/device")
public class TDeviceController extends BaseController {
    
    @Autowired
    private ITDeviceService tDeviceService;

    /**
     * 查询设备列表
     */
    @RequiresPermissions("system:device:list")
    @GetMapping("/list")
    public TableDataInfo list(TDevice tDevice) {
        startPage();
        List<TDeviceVO> list = tDeviceService.selectTDeviceList(tDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @RequiresPermissions("system:device:export")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDevice tDevice) {
        List<TDeviceVO> list = tDeviceService.selectTDeviceList(tDevice);
        ExcelUtil<TDeviceVO> util = new ExcelUtil<TDeviceVO>(TDeviceVO.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @RequiresPermissions("system:device:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tDeviceService.selectTDeviceById(id));
    }

    /**
     * 新增设备
     */
    @RequiresPermissions("system:device:add")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TDeviceDTO tDeviceDTO) {
        if ("1".equals(tDeviceService.checkDeviceCodeUnique(tDeviceDTO.getDeviceCode()))) {
            return AjaxResult.error("新增设备'" + tDeviceDTO.getDeviceName() + "'失败，设备编号已存在");
        }
        return toAjax(tDeviceService.insertTDevice(tDeviceDTO));
    }

    /**
     * 修改设备
     */
    @RequiresPermissions("system:device:edit")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody TDeviceDTO tDeviceDTO) {
        return toAjax(tDeviceService.updateTDevice(tDeviceDTO));
    }

    /**
     * 保存设备（新增或修改）
     */
    @PostMapping
    public AjaxResult save(@Validated @RequestBody TDeviceDTO tDeviceDTO) {
        if (tDeviceDTO.getId() != null) {
            // 修改操作
            return toAjax(tDeviceService.updateTDevice(tDeviceDTO));
        } else {
            // 新增操作
            if ("1".equals(tDeviceService.checkDeviceCodeUnique(tDeviceDTO.getDeviceCode()))) {
                return AjaxResult.error("新增设备'" + tDeviceDTO.getDeviceName() + "'失败，设备编号已存在");
            }
            return toAjax(tDeviceService.insertTDevice(tDeviceDTO));
        }
    }

    /**
     * 删除设备
     */
    @RequiresPermissions("system:device:remove")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(tDeviceService.deleteTDeviceByIds(ids));
    }
}