package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 老鼠视图对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TMouseVO {
    
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
    @Excel(name = "性别")
    private String gender;

    /** 性别描述 */
    private String genderName;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 体重 */
    @Excel(name = "体重")
    private BigDecimal weight;

    /** 实验ID */
    private Long experimentId;

    /** 实验名称 */
    @Excel(name = "实验名称")
    private String experimentName;

    /** 照片URL（导出为图片） */
    @Excel(name = "照片", cellType = Excel.ColumnType.IMAGE, width = 20, height = 40)
    private String photoUrl;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态")
    private Integer status;

    /** 状态描述 */
    private String statusName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 备注 */
    private String remark;
}