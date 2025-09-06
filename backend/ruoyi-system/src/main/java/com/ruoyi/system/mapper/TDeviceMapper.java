package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TDevice;
import com.ruoyi.system.domain.vo.TDeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface TDeviceMapper {
    
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
     * @param tDevice 设备
     * @return 结果
     */
    public int insertTDevice(TDevice tDevice);

    /**
     * 修改设备
     * 
     * @param tDevice 设备
     * @return 结果
     */
    public int updateTDevice(TDevice tDevice);

    /**
     * 删除设备
     * 
     * @param id 设备主键
     * @return 结果
     */
    public int deleteTDeviceById(Long id);

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTDeviceByIds(Long[] ids);

    /**
     * 校验设备编号是否唯一
     * 
     * @param deviceCode 设备编号
     * @return 结果
     */
    public TDevice checkDeviceCodeUnique(@Param("deviceCode") String deviceCode);
}