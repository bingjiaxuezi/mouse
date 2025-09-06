package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 实验视图对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TExperimentVO {
    
    /** 实验ID */
    private Long id;

    /** 实验名称 */
    private String experimentName;

    /** 实验编号 */
    private String experimentCode;

    /** 实验描述 */
    private String description;

    /** 研究员ID */
    private Long researcherId;

    /** 研究员姓名 */
    private String researcherName;

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