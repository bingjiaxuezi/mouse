package com.ruoyi.system.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 设备数据传输对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TDeviceDTO {
    
    /** 设备ID */
    private Long id;

    /** 设备编号 */
    @NotBlank(message = "设备编号不能为空")
    @Size(max = 50, message = "设备编号长度不能超过50个字符")
    private String deviceCode;

    /** 设备名称 */
    @NotBlank(message = "设备名称不能为空")
    @Size(max = 100, message = "设备名称长度不能超过100个字符")
    private String deviceName;

    /** 设备类型 */
    @Size(max = 20, message = "设备类型长度不能超过20个字符")
    private String deviceType;

    /** 设备位置 */
    @Size(max = 100, message = "设备位置长度不能超过100个字符")
    private String location;

    /** 状态（1正常 0停用） */
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}