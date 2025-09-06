package com.ruoyi.system.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 实验数据传输对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TExperimentDTO {
    
    /** 实验ID */
    private Long id;

    /** 实验名称 */
    @NotBlank(message = "实验名称不能为空")
    @Size(max = 100, message = "实验名称长度不能超过100个字符")
    private String experimentName;

    /** 实验编号 */
    @NotBlank(message = "实验编号不能为空")
    @Size(max = 50, message = "实验编号长度不能超过50个字符")
    private String experimentCode;

    /** 实验描述 */
    private String description;

    /** 研究员ID */
    private Long researcherId;

    /** 状态（1正常 0停用） */
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}