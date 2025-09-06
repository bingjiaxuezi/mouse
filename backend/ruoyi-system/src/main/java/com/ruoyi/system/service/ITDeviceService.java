package com.ruoyi.system.service;

import com.ruoyi.system.domain.TDevice;
import com.ruoyi.system.domain.dto.TDeviceDTO;
import com.ruoyi.system.domain.vo.TDeviceVO;

import java.util.List;

/**
 * 设备Service接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface ITDeviceService {
    
    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    public TDeviceVO selectTDeviceById(Long id);

    /**
     * 查询设备列表
     * 
     * @param tDevice 设备
     * @return 设备集合
     */
    public List<TDeviceVO> selectTDeviceList(TDevice tDevice);

    /**
     * 新增设备
     * 
     * @param tDeviceDTO 设备
     * @return 结果
     */
    public int insertTDevice(TDeviceDTO tDeviceDTO);

    /**
     * 修改设备
     * 
     * @param tDeviceDTO 设备
     * @return 结果
     */
    public int updateTDevice(TDeviceDTO tDeviceDTO);

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteTDeviceByIds(Long[] ids);

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    public int deleteTDeviceById(Long id);

    /**
     * 校验设备编号是否唯一
     * 
     * @param deviceCode 设备编号
     * @return 结果
     */
    public String checkDeviceCodeUnique(String deviceCode);
}