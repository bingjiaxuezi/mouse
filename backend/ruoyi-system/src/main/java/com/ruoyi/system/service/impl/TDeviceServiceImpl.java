package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TDevice;
import com.ruoyi.system.domain.dto.TDeviceDTO;
import com.ruoyi.system.domain.vo.TDeviceVO;
import com.ruoyi.system.mapper.TDeviceMapper;
import com.ruoyi.system.service.ITDeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
public class TDeviceServiceImpl implements ITDeviceService {
    
    @Autowired
    private TDeviceMapper tDeviceMapper;

    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public TDeviceVO selectTDeviceById(Long id) {
        return tDeviceMapper.selectTDeviceById(id);
    }

    /**
     * 查询设备列表
     * 
     * @param tDevice 设备
     * @return 设备
     */
    @Override
    public List<TDeviceVO> selectTDeviceList(TDevice tDevice) {
        return tDeviceMapper.selectTDeviceList(tDevice);
    }

    /**
     * 新增设备
     * 
     * @param tDeviceDTO 设备
     * @return 结果
     */
    @Override
    public int insertTDevice(TDeviceDTO tDeviceDTO) {
        TDevice tDevice = new TDevice();
        BeanUtils.copyProperties(tDeviceDTO, tDevice);
        tDevice.setCreateTime(DateUtils.getNowDate());
        return tDeviceMapper.insertTDevice(tDevice);
    }

    /**
     * 修改设备
     * 
     * @param tDeviceDTO 设备
     * @return 结果
     */
    @Override
    public int updateTDevice(TDeviceDTO tDeviceDTO) {
        TDevice tDevice = new TDevice();
        BeanUtils.copyProperties(tDeviceDTO, tDevice);
        tDevice.setUpdateTime(DateUtils.getNowDate());
        return tDeviceMapper.updateTDevice(tDevice);
    }

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteTDeviceByIds(Long[] ids) {
        return tDeviceMapper.deleteTDeviceByIds(ids);
    }

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    @Override
    public int deleteTDeviceById(Long id) {
        return tDeviceMapper.deleteTDeviceById(id);
    }

    /**
     * 校验设备编号是否唯一
     * 
     * @param deviceCode 设备编号
     * @return 结果
     */
    @Override
    public String checkDeviceCodeUnique(String deviceCode) {
        TDevice device = tDeviceMapper.checkDeviceCodeUnique(deviceCode);
        if (StringUtils.isNotNull(device)) {
            return "1";
        }
        return "0";
    }
}