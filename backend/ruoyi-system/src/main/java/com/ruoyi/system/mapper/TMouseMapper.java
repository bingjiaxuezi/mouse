package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TMouse;
import com.ruoyi.system.domain.vo.TMouseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老鼠Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface TMouseMapper extends BaseMapper<TMouse> {
    
    /**
     * 查询老鼠
     * 
     * @param id 老鼠主键
     * @return 老鼠
     */
    public TMouseVO selectTMouseById(Long id);

    /**
     * 查询老鼠列表
     * 
     * @param tMouse 老鼠
     * @return 老鼠集合
     */
    public List<TMouseVO> selectTMouseList(TMouse tMouse);

    /**
     * 新增老鼠
     * 
     * @param tMouse 老鼠
     * @return 结果
     */
    public int insertTMouse(TMouse tMouse);

    /**
     * 修改老鼠
     * 
     * @param tMouse 老鼠
     * @return 结果
     */
    public int updateTMouse(TMouse tMouse);

    /**
     * 删除老鼠
     * 
     * @param id 老鼠主键
     * @return 结果
     */
    public int deleteTMouseById(Long id);

    /**
     * 批量删除老鼠
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTMouseByIds(Long[] ids);

    /**
     * 校验老鼠编号是否唯一
     * 
     * @param mouseCode 老鼠编号
     * @return 结果
     */
    public TMouse checkMouseCodeUnique(@Param("mouseCode") String mouseCode);

    /**
     * 根据老鼠编号查询老鼠信息
     * 
     * @param mouseCode 老鼠编号
     * @return 老鼠信息
     */
    public TMouseVO selectTMouseByMouseCode(@Param("mouseCode") String mouseCode);
}