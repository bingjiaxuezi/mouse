package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 设备视图对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TDeviceVO {
    
    /** 设备ID */
    private Long id;

    /** 设备编号 */
    private String deviceCode;

    /** 设备名称 */
    private String deviceName;

    /** 设备类型 */
    private String deviceType;

    /** 设备位置 */
    private String location;

    /** 状态（1正常 0停用） */
    private Integer status;

    /** 状态描述 */
    private String statusName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;
}