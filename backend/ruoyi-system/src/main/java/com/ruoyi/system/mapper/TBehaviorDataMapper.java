package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TBehaviorData;
import com.ruoyi.system.domain.vo.TBehaviorDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行为数据Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface TBehaviorDataMapper {
    
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
     * @param tBehaviorData 行为数据
     * @return 结果
     */
    public int insertTBehaviorData(TBehaviorData tBehaviorData);

    /**
     * 修改行为数据
     * 
     * @param tBehaviorData 行为数据
     * @return 结果
     */
    public int updateTBehaviorData(TBehaviorData tBehaviorData);

    /**
     * 删除行为数据
     * 
     * @param id 行为数据主键
     * @return 结果
     */
    public int deleteTBehaviorDataById(Long id);

    /**
     * 批量删除行为数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBehaviorDataByIds(Long[] ids);

    /**
     * 根据小鼠编码获取最近行为记录
     * 
     * @param mouseCode 小鼠编码
     * @param limit 记录数量限制
     * @return 最近行为记录列表
     */
    public List<TBehaviorDataVO> selectRecentBehaviorsByMouseCode(@Param("mouseCode") String mouseCode, @Param("limit") Integer limit);
}