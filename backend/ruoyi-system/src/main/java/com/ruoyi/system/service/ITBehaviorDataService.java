package com.ruoyi.system.service;

import com.ruoyi.system.domain.TBehaviorData;
import com.ruoyi.system.domain.dto.TBehaviorDataDTO;
import com.ruoyi.system.domain.vo.TBehaviorDataVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 行为数据Service接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface ITBehaviorDataService {
    
    /**
     * 查询行为数据
     * 
     * @param id 行为数据主键
     * @return 行为数据
     */
    public TBehaviorDataVO selectTBehaviorDataById(Long id);

    /**
     * 查询行为数据列表
     * 
     * @param tBehaviorData 行为数据
     * @return 行为数据集合
     */
    public List<TBehaviorDataVO> selectTBehaviorDataList(TBehaviorData tBehaviorData);

    /**
     * 新增行为数据
     * 
     * @param tBehaviorDataDTO 行为数据
     * @return 结果
     */
    public int insertTBehaviorData(TBehaviorDataDTO tBehaviorDataDTO);

    /**
     * 修改行为数据
     * 
     * @param tBehaviorDataDTO 行为数据
     * @return 结果
     */
    public int updateTBehaviorData(TBehaviorDataDTO tBehaviorDataDTO);

    /**
     * 批量删除行为数据
     * 
     * @param ids 需要删除的行为数据主键集合
     * @return 结果
     */
    public int deleteTBehaviorDataByIds(Long[] ids);

    /**
     * 删除行为数据信息
     * 
     * @param id 行为数据主键
     * @return 结果
     */
    public int deleteTBehaviorDataById(Long id);

    /**
     * 根据小鼠编码获取行为统计数据
     * 
     * @param mouseCode 小鼠编码
     * @return 统计数据
     */
    public Map<String, Object> getMouseBehaviorStatistics(String mouseCode);

    /**
     * 根据小鼠编码获取最近行为记录
     * 
     * @param mouseCode 小鼠编码
     * @param limit 记录数量限制
     * @return 最近行为记录列表
     */
    public List<TBehaviorDataVO> getRecentBehaviorsByMouseCode(String mouseCode, Integer limit);

    /**
     * 根据小鼠编码获取行为汇总信息
     * 
     * @param mouseCode 小鼠编码
     * @return 行为汇总信息
     */
    Map<String, Object> getMouseBehaviorSummary(String mouseCode);

    /**
     * 根据小鼠编码获取行为趋势数据
     * 
     * @param mouseCode 小鼠编码
     * @return 行为趋势数据
     */
    Map<String, Object> getMouseBehaviorTrend(String mouseCode);

    /**
     * 根据小鼠编码获取行为类型分布数据
     * 
     * @param mouseCode 小鼠编码
     * @return 行为类型分布数据
     */
    Map<String, Object> getMouseBehaviorDistribution(String mouseCode);

    /**
     * 获取行为类型分布统计数据（支持时间范围查询）
     * 
     * @param mouseCode 小鼠编码（可选）
     * @param startTime 开始时间（可选）
     * @param endTime 结束时间（可选）
     * @param hours 最近小时数（可选，默认24小时）
     * @return 行为类型分布数据
     */
    Map<String, Object> getBehaviorTypeDistribution(String mouseCode, Date startTime, Date endTime, Integer hours);
}