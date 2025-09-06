package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TBehaviorData;
import com.ruoyi.system.domain.dto.TBehaviorDataDTO;
import com.ruoyi.system.domain.vo.TBehaviorDataVO;
import com.ruoyi.system.mapper.TBehaviorDataMapper;
import com.ruoyi.system.service.ITBehaviorDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Date;
import java.util.Calendar;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * 行为数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
public class TBehaviorDataServiceImpl implements ITBehaviorDataService {
    
    @Autowired
    private TBehaviorDataMapper tBehaviorDataMapper;

    /**
     * 查询行为数据
     * 
     * @param id 行为数据主键
     * @return 行为数据
     */
    @Override
    public TBehaviorDataVO selectTBehaviorDataById(Long id) {
        return tBehaviorDataMapper.selectTBehaviorDataById(id);
    }

    /**
     * 查询行为数据列表
     * 
     * @param tBehaviorData 行为数据
     * @return 行为数据
     */
    @Override
    public List<TBehaviorDataVO> selectTBehaviorDataList(TBehaviorData tBehaviorData) {
        return tBehaviorDataMapper.selectTBehaviorDataList(tBehaviorData);
    }

    /**
     * 新增行为数据
     * 
     * @param tBehaviorDataDTO 行为数据
     * @return 结果
     */
    @Override
    public int insertTBehaviorData(TBehaviorDataDTO tBehaviorDataDTO) {
        TBehaviorData tBehaviorData = new TBehaviorData();
        BeanUtils.copyProperties(tBehaviorDataDTO, tBehaviorData);
        tBehaviorData.setCreateTime(DateUtils.getNowDate());
        return tBehaviorDataMapper.insertTBehaviorData(tBehaviorData);
    }

    /**
     * 修改行为数据
     * 
     * @param tBehaviorDataDTO 行为数据
     * @return 结果
     */
    @Override
    public int updateTBehaviorData(TBehaviorDataDTO tBehaviorDataDTO) {
        TBehaviorData tBehaviorData = new TBehaviorData();
        BeanUtils.copyProperties(tBehaviorDataDTO, tBehaviorData);
        tBehaviorData.setUpdateTime(DateUtils.getNowDate());
        return tBehaviorDataMapper.updateTBehaviorData(tBehaviorData);
    }

    /**
     * 批量删除行为数据
     * 
     * @param ids 需要删除的行为数据主键
     * @return 结果
     */
    @Override
    public int deleteTBehaviorDataByIds(Long[] ids) {
        return tBehaviorDataMapper.deleteTBehaviorDataByIds(ids);
    }

    /**
     * 删除行为数据信息
     * 
     * @param id 行为数据主键
     * @return 结果
     */
    @Override
    public int deleteTBehaviorDataById(Long id) {
        return tBehaviorDataMapper.deleteTBehaviorDataById(id);
    }

    /**
     * 根据小鼠编码获取行为统计数据
     * 
     * @param mouseCode 小鼠编码
     * @return 统计数据
     */
    @Override
    public Map<String, Object> getMouseBehaviorStatistics(String mouseCode) {
        // 构建查询条件
        TBehaviorData queryParam = new TBehaviorData();
        queryParam.setMouseCode(mouseCode);
        
        // 获取行为数据列表
        List<TBehaviorDataVO> behaviorList = tBehaviorDataMapper.selectTBehaviorDataList(queryParam);
        
        // 统计数据
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalRecords", behaviorList.size());
        
        // 统计行为类型数量
        long behaviorTypeCount = behaviorList.stream()
            .map(TBehaviorDataVO::getBehaviorType)
            .distinct()
            .count();
        statistics.put("behaviorTypeCount", behaviorTypeCount);
        
        // 获取最近记录时间
        if (!behaviorList.isEmpty()) {
            statistics.put("lastRecordTime", behaviorList.get(0).getTimestamp());
        } else {
            statistics.put("lastRecordTime", null);
        }
        
        return statistics;
    }

    /**
     * 根据小鼠编码获取最近行为记录
     * 
     * @param mouseCode 小鼠编码
     * @param limit 记录数量限制
     * @return 最近行为记录列表
     */
    @Override
    public List<TBehaviorDataVO> getRecentBehaviorsByMouseCode(String mouseCode, Integer limit) {
        return tBehaviorDataMapper.selectRecentBehaviorsByMouseCode(mouseCode, limit);
    }

    /**
     * 根据小鼠编码获取行为汇总信息
     * 
     * @param mouseCode 小鼠编码
     * @return 行为汇总信息
     */
    @Override
    public Map<String, Object> getMouseBehaviorSummary(String mouseCode) {
        // 构建查询条件
        TBehaviorData queryParam = new TBehaviorData();
        queryParam.setMouseCode(mouseCode);
        
        // 获取行为数据列表
        List<TBehaviorDataVO> behaviorList = tBehaviorDataMapper.selectTBehaviorDataList(queryParam);
        
        // 统计数据
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalRecords", behaviorList.size());
        
        // 统计今日记录数
        long todayRecords = behaviorList.stream()
            .filter(behavior -> {
                if (behavior.getTimestamp() != null) {
                    LocalDate today = LocalDate.now();
                    LocalDate recordDate = behavior.getTimestamp().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                    return recordDate.equals(today);
                }
                return false;
            })
            .count();
        summary.put("todayRecords", todayRecords);
        
        // 统计行为类型数量
        long behaviorTypeCount = behaviorList.stream()
            .map(TBehaviorDataVO::getBehaviorType)
            .filter(Objects::nonNull)
            .distinct()
            .count();
        summary.put("behaviorTypeCount", behaviorTypeCount);
        
        // 设置平均持续时间为0（暂时没有持续时间字段）
        summary.put("avgDuration", 0.0);
        
        // 获取最近记录时间
        if (!behaviorList.isEmpty()) {
            summary.put("lastRecordTime", behaviorList.get(0).getTimestamp());
        } else {
            summary.put("lastRecordTime", null);
        }
        
        return summary;
    }

    /**
     * 根据小鼠编码获取行为趋势数据
     */
    @Override
    public Map<String, Object> getMouseBehaviorTrend(String mouseCode) {
        Map<String, Object> trendData = new HashMap<>();
        
        // 获取最近24小时的行为数据，按小时分组
        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - 24 * 60 * 60 * 1000); // 24小时前
        
        // 构建查询条件
        TBehaviorData queryParam = new TBehaviorData();
        queryParam.setMouseCode(mouseCode);
        Map<String, Object> params = new HashMap<>();
        params.put("beginTimestamp", startTime);
        params.put("endTimestamp", endTime);
        queryParam.setParams(params);
        
        // 查询行为数据
        List<TBehaviorDataVO> behaviorList = tBehaviorDataMapper.selectTBehaviorDataList(queryParam);
        
        // 按小时分组统计
        Map<Integer, Integer> hourlyCount = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            hourlyCount.put(i, 0);
        }
        
        for (TBehaviorDataVO behavior : behaviorList) {
            if (behavior.getTimestamp() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(behavior.getTimestamp());
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                hourlyCount.put(hour, hourlyCount.get(hour) + 1);
            }
        }
        
        // 构建图表数据
        List<String> hours = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hours.add(String.format("%02d:00", i));
            counts.add(hourlyCount.get(i));
        }
        
        trendData.put("hours", hours);
        
        // 构建series数据
        Map<String, Object> seriesItem = new HashMap<>();
        seriesItem.put("name", "行为记录数");
        seriesItem.put("type", "line");
        seriesItem.put("data", counts);
        
        trendData.put("series", Arrays.asList(seriesItem));
        trendData.put("legend", Arrays.asList("行为记录数"));
        
        return trendData;
    }

    /**
     * 根据小鼠编码获取行为类型分布数据
     */
    @Override
    public Map<String, Object> getMouseBehaviorDistribution(String mouseCode) {
        Map<String, Object> distributionData = new HashMap<>();
        
        // 构建查询条件
        TBehaviorData queryParam = new TBehaviorData();
        queryParam.setMouseCode(mouseCode);
        
        // 查询行为数据
        List<TBehaviorDataVO> behaviorList = tBehaviorDataMapper.selectTBehaviorDataList(queryParam);
        
        // 按行为类型分组统计
        Map<String, Integer> typeCount = new HashMap<>();
        for (TBehaviorDataVO behavior : behaviorList) {
            String behaviorType = behavior.getBehaviorTypeName();
            if (behaviorType != null) {
                typeCount.put(behaviorType, typeCount.getOrDefault(behaviorType, 0) + 1);
            }
        }
        
        // 构建饼图数据
        List<String> legend = new ArrayList<>();
        List<Map<String, Object>> pieData = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
            legend.add(entry.getKey());
            Map<String, Object> item = new HashMap<>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            pieData.add(item);
        }
        
        distributionData.put("legend", legend);
        distributionData.put("data", pieData);
        
        return distributionData;
    }

    /**
     * 获取行为类型分布统计数据（支持时间范围查询）
     * 
     * @param mouseCode 小鼠编码（可选）
     * @param startTime 开始时间（可选）
     * @param endTime 结束时间（可选）
     * @param hours 最近小时数（可选，默认24小时）
     * @return 行为类型分布数据
     */
    @Override
    public Map<String, Object> getBehaviorTypeDistribution(String mouseCode, Date startTime, Date endTime, Integer hours) {
        Map<String, Object> result = new HashMap<>();
        
        // 构建查询参数
        TBehaviorData queryParam = new TBehaviorData();
        if (mouseCode != null && !mouseCode.trim().isEmpty()) {
            queryParam.setMouseCode(mouseCode);
        }
        
        // 处理时间范围
        Date queryStartTime = startTime;
        Date queryEndTime = endTime;
        
        // 如果没有指定时间范围，使用hours参数（默认24小时）
        if (queryStartTime == null && queryEndTime == null) {
            int hoursToQuery = (hours != null && hours > 0) ? hours : 24;
            Calendar cal = Calendar.getInstance();
            queryEndTime = cal.getTime();
            cal.add(Calendar.HOUR_OF_DAY, -hoursToQuery);
            queryStartTime = cal.getTime();
        }
        
        // 设置时间范围到查询参数
        queryParam.setParams(new HashMap<>());
        if (queryStartTime != null) {
            queryParam.getParams().put("beginTime", queryStartTime);
        }
        if (queryEndTime != null) {
            queryParam.getParams().put("endTime", queryEndTime);
        }
        
        // 查询行为数据
        List<TBehaviorDataVO> behaviorList = tBehaviorDataMapper.selectTBehaviorDataList(queryParam);
        
        // 按行为类型分组统计
        Map<String, Integer> typeCount = new HashMap<>();
        int totalCount = 0;
        
        for (TBehaviorDataVO behavior : behaviorList) {
            String behaviorType = behavior.getBehaviorTypeName();
            if (behaviorType != null && !behaviorType.trim().isEmpty()) {
                typeCount.put(behaviorType, typeCount.getOrDefault(behaviorType, 0) + 1);
                totalCount++;
            }
        }
        
        // 构建饼图数据格式
        List<String> legend = new ArrayList<>();
        List<Map<String, Object>> pieData = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
            legend.add(entry.getKey());
            Map<String, Object> item = new HashMap<>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            pieData.add(item);
        }
        
        // 构建返回结果
        result.put("legend", legend);
        result.put("data", pieData);
        result.put("totalCount", totalCount);
        result.put("typeCount", typeCount.size());
        result.put("queryStartTime", queryStartTime);
        result.put("queryEndTime", queryEndTime);
        result.put("mouseCode", mouseCode);
        
        return result;
    }
}