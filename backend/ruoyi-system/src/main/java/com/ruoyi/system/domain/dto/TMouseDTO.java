package com.ruoyi.system.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 老鼠数据传输对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TMouseDTO {
    
    /** 老鼠ID */
    private Long id;

    /** 老鼠编号 */
    @NotBlank(message = "老鼠编号不能为空")
    @Size(max = 50, message = "老鼠编号长度不能超过50个字符")
    private String mouseCode;

    /** 老鼠名称 */
    @Size(max = 50, message = "老鼠名称长度不能超过50个字符")
    private String mouseName;

    /** 物种 */
    @Size(max = 50, message = "物种长度不能超过50个字符")
    private String species;

    /** 性别 */
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /** 体重 */
    private BigDecimal weight;

    /** 实验ID */
    private Long experimentId;

    /** 照片URL */
    private String photoUrl;

    /** 状态（1正常 0停用） */
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}