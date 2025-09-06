package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 老鼠对象 t_mouse
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TMouse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 老鼠ID */
    private Long id;

    /** 老鼠编号 */
    @Excel(name = "老鼠编号")
    private String mouseCode;

    /** 老鼠名称 */
    @Excel(name = "老鼠名称")
    private String mouseName;

    /** 物种 */
    @Excel(name = "物种")
    private String species;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "male=雄性,female=雌性")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 体重 */
    @Excel(name = "体重")
    private BigDecimal weight;

    /** 实验ID */
    @Excel(name = "实验ID")
    private Long experimentId;

    /** 照片URL */
    private String photoUrl;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}