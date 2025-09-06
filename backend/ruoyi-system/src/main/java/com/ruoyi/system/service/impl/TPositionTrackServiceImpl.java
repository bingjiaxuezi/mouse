package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TPositionTrack;
import com.ruoyi.system.domain.dto.TPositionTrackDTO;
import com.ruoyi.system.domain.vo.TPositionTrackVO;
import com.ruoyi.system.mapper.TPositionTrackMapper;
import com.ruoyi.system.service.ITPositionTrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 位置跟踪Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
public class TPositionTrackServiceImpl implements ITPositionTrackService {
    
    @Autowired
    private TPositionTrackMapper tPositionTrackMapper;

    /**
     * 查询位置跟踪
     * 
     * @param id 位置跟踪主键
     * @return 位置跟踪
     */
    @Override
    public TPositionTrackVO selectTPositionTrackById(Long id) {
        return tPositionTrackMapper.selectTPositionTrackById(id);
    }

    /**
     * 查询位置跟踪列表
     * 
     * @param tPositionTrack 位置跟踪
     * @return 位置跟踪
     */
    @Override
    public List<TPositionTrackVO> selectTPositionTrackList(TPositionTrack tPositionTrack) {
        return tPositionTrackMapper.selectTPositionTrackList(tPositionTrack);
    }

    /**
     * 新增位置跟踪
     * 
     * @param tPositionTrackDTO 位置跟踪
     * @return 结果
     */
    @Override
    public int insertTPositionTrack(TPositionTrackDTO tPositionTrackDTO) {
        TPositionTrack tPositionTrack = new TPositionTrack();
        BeanUtils.copyProperties(tPositionTrackDTO, tPositionTrack);
        tPositionTrack.setCreateTime(DateUtils.getNowDate());
        return tPositionTrackMapper.insertTPositionTrack(tPositionTrack);
    }

    /**
     * 修改位置跟踪
     * 
     * @param tPositionTrackDTO 位置跟踪
     * @return 结果
     */
    @Override
    public int updateTPositionTrack(TPositionTrackDTO tPositionTrackDTO) {
        TPositionTrack tPositionTrack = new TPositionTrack();
        BeanUtils.copyProperties(tPositionTrackDTO, tPositionTrack);
        return tPositionTrackMapper.updateTPositionTrack(tPositionTrack);
    }

    /**
     * 批量删除位置跟踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    @Override
    public int deleteTPositionTrackByIds(Long[] ids) {
        return tPositionTrackMapper.deleteTPositionTrackByIds(ids);
    }

    /**
     * 删除位置跟踪信息
     * 
     * @param id 位置跟踪主键
     * @return 结果
     */
    @Override
    public int deleteTPositionTrackById(Long id) {
        return tPositionTrackMapper.deleteTPositionTrackById(id);
    }

    /**
     * 根据小鼠编码删除位置跟踪
     * 
     * @param mouseCode 小鼠编码
     * @return 结果
     */
    @Override
    public int deleteTPositionTrackByMouseCode(String mouseCode) {
        return tPositionTrackMapper.deleteTPositionTrackByMouseCode(mouseCode);
    }
}