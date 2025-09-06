package com.ruoyi.system.service;

import com.ruoyi.system.domain.TPositionTrack;
import com.ruoyi.system.domain.dto.TPositionTrackDTO;
import com.ruoyi.system.domain.vo.TPositionTrackVO;

import java.util.List;

/**
 * 位置跟踪Service接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface ITPositionTrackService {
    
    /**
     * 查询位置跟踪
     * 
     * @param id 位置跟踪主键
     * @return 位置跟踪
     */
    public TPositionTrackVO selectTPositionTrackById(Long id);

    /**
     * 查询位置跟踪列表
     * 
     * @param tPositionTrack 位置跟踪
     * @return 位置跟踪集合
     */
    public List<TPositionTrackVO> selectTPositionTrackList(TPositionTrack tPositionTrack);

    /**
     * 新增位置跟踪
     * 
     * @param tPositionTrackDTO 位置跟踪
     * @return 结果
     */
    public int insertTPositionTrack(TPositionTrackDTO tPositionTrackDTO);

    /**
     * 修改位置跟踪
     * 
     * @param tPositionTrackDTO 位置跟踪
     * @return 结果
     */
    public int updateTPositionTrack(TPositionTrackDTO tPositionTrackDTO);

    /**
     * 批量删除位置跟踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPositionTrackByIds(Long[] ids);

    /**
     * 删除位置跟踪信息
     * 
     * @param id 位置跟踪主键
     * @return 结果
     */
    public int deleteTPositionTrackById(Long id);

    /**
     * 根据小鼠编码删除位置跟踪
     * 
     * @param mouseCode 小鼠编码
     * @return 结果
     */
    public int deleteTPositionTrackByMouseCode(String mouseCode);
}