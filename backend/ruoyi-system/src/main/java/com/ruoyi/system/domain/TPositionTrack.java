package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 位置跟踪对象 t_position_track
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TPositionTrack extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 位置跟踪ID */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;

    /** 老鼠编号 */
    @Excel(name = "老鼠编号")
    private String mouseCode;

    /** 实验ID */
    @Excel(name = "实验ID")
    private Long experimentId;

    /** X坐标 */
    @Excel(name = "X坐标")
    private BigDecimal xCoordinate;

    /** Y坐标 */
    @Excel(name = "Y坐标")
    private BigDecimal yCoordinate;

    /** Z坐标 */
    @Excel(name = "Z坐标")
    private BigDecimal zCoordinate;

    /** 速度 */
    @Excel(name = "速度")
    private BigDecimal velocity;

    /** 方向 */
    @Excel(name = "方向")
    private BigDecimal direction;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}