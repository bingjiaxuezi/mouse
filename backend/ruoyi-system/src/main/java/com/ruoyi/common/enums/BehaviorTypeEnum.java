package com.ruoyi.common.enums;

/**
 * 行为类型枚举
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public enum BehaviorTypeEnum {
    
    SLEEP("SLEEP", "睡眠", "basic", "小鼠处于睡眠状态，活动量极低", "分钟"),
    EAT("EAT", "进食", "basic", "小鼠进食行为", "次数"),
    DRINK("DRINK", "饮水", "basic", "小鼠饮水行为", "次数"),
    DEFECATE("DEFECATE", "排便", "basic", "小鼠排便行为", "次数"),
    COUGH("COUGH", "咳嗽", "abnormal", "小鼠咳嗽行为，可能表示呼吸道问题", "次数"),
    GROOM("GROOM", "舔毛", "basic", "小鼠自我清洁行为", "分钟"),
    WALK("WALK", "走动", "basic", "小鼠正常移动行为", "米"),
    RUN("RUN", "奔跑", "basic", "小鼠快速移动行为", "次数"),
    CLIMB("CLIMB", "攀爬", "basic", "小鼠攀爬行为", "次数"),
    EXPLORE("EXPLORE", "探索", "basic", "小鼠探索环境行为", "分钟"),
    REST("REST", "休息", "basic", "小鼠静止休息但未睡眠", "分钟"),
    SOCIAL("SOCIAL", "社交", "social", "多只小鼠间的社交互动", "次数"),
    AGGRESSIVE("AGGRESSIVE", "攻击", "social", "小鼠攻击性行为", "次数"),
    MATING("MATING", "交配", "social", "小鼠交配行为", "次数"),
    NEST("NEST", "筑巢", "basic", "小鼠筑巢整理行为", "次数"),
    FREEZE("FREEZE", "僵直", "abnormal", "小鼠恐惧或应激反应", "次数"),
    CIRCLE("CIRCLE", "转圈", "abnormal", "小鼠异常转圈行为", "次数"),
    SEIZURE("SEIZURE", "癫痫", "abnormal", "小鼠癫痫发作", "次数"),
    SCRATCH("SCRATCH", "抓挠", "basic", "小鼠抓挠行为", "次数"),
    JUMPING("JUMPING", "跳跃", "basic", "小鼠跳跃行为", "次数"),
    LEARNING("LEARNING", "学习", "social", "小鼠学习行为", "次数"),
    
    // 兼容旧数据的映射
    MOVEMENT("MOVEMENT", "运动", "basic", "小鼠运动行为", "次数"),
    SLEEPING("SLEEPING", "睡眠", "basic", "小鼠睡眠行为", "分钟"),
    NORMAL("NORMAL", "正常", "basic", "小鼠正常行为", "次数"),
    GROOMING("GROOMING", "梳理", "basic", "小鼠梳理行为", "分钟"),
    REPETITIVE("REPETITIVE", "重复行为", "abnormal", "小鼠重复性行为", "次数"),
    FEEDING("FEEDING", "进食", "basic", "小鼠进食行为", "次数"),
    RESTING("RESTING", "休息", "basic", "小鼠休息行为", "分钟"),
    ANXIETY("ANXIETY", "焦虑", "abnormal", "小鼠焦虑行为", "次数"),
    EXPLORATION("EXPLORATION", "探索", "basic", "小鼠探索行为", "分钟"),
    EXPLORING("EXPLORING", "探索", "basic", "小鼠探索行为", "分钟"),
    ABNORMAL("ABNORMAL", "异常", "abnormal", "小鼠异常行为", "次数"),
    STEREOTYPY("STEREOTYPY", "刻板行为", "abnormal", "小鼠刻板行为", "次数"),
    RUNNING("RUNNING", "奔跑", "basic", "小鼠奔跑行为", "次数"),
    CLIMBING("CLIMBING", "攀爬", "basic", "小鼠攀爬行为", "次数"),
    DRINKING("DRINKING", "饮水", "basic", "小鼠饮水行为", "次数");
    
    private final String code;
    private final String name;
    private final String category;
    private final String description;
    private final String unit;
    
    BehaviorTypeEnum(String code, String name, String category, String description, String unit) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.description = description;
        this.unit = unit;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getUnit() {
        return unit;
    }
    
    /**
     * 根据行为编码获取中文名称
     * 
     * @param code 行为编码
     * @return 中文名称
     */
    public static String getNameByCode(String code) {
        if (code == null) {
            return "未知";
        }
        
        for (BehaviorTypeEnum type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type.getName();
            }
        }
        
        // 如果找不到对应的枚举，返回原始值
        return code;
    }
    
    /**
     * 根据行为编码获取枚举对象
     * 
     * @param code 行为编码
     * @return 枚举对象
     */
    public static BehaviorTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        
        for (BehaviorTypeEnum type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        
        return null;
    }
    
    /**
     * 获取所有行为类型的编码和名称映射
     * 
     * @return 编码-名称映射
     */
    public static java.util.Map<String, String> getAllCodeNameMap() {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        for (BehaviorTypeEnum type : values()) {
            map.put(type.getCode(), type.getName());
        }
        return map;
    }
    
    /**
     * 根据分类获取行为类型列表
     * 
     * @param category 分类
     * @return 行为类型列表
     */
    public static java.util.List<BehaviorTypeEnum> getByCategory(String category) {
        java.util.List<BehaviorTypeEnum> result = new java.util.ArrayList<>();
        for (BehaviorTypeEnum type : values()) {
            if (type.getCategory().equals(category)) {
                result.add(type);
            }
        }
        return result;
    }
}