package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 设备对象 t_device
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TDevice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 设备位置 */
    @Excel(name = "设备位置")
    private String location;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}