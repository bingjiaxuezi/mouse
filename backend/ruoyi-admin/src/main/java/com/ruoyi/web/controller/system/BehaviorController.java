package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.BehaviorTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.system.domain.TBehaviorData;
import com.ruoyi.system.domain.vo.TBehaviorDataVO;
import com.ruoyi.system.service.ITBehaviorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行为管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Controller
@RequestMapping("/system/behavior")
public class BehaviorController extends BaseController {
    
    private String prefix = "system/behavior";
    
    @Autowired
    private ITBehaviorDataService tBehaviorDataService;

    /**
     * 行为管理主页面
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping()
    public String behavior() {
        return prefix + "/behavior";
    }

    /**
     * 小鼠行为详情页面
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/mouse/{mouseCode}")
    public String mouseBehavior(@PathVariable("mouseCode") String mouseCode, ModelMap mmap) {
        mmap.put("mouseCode", mouseCode);
        return "system/behavior/mouse-behavior";
    }

    /**
     * 行为记录详情页面
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/detail/{id}")
    public String behaviorDetail(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("behaviorId", id);
        return prefix + "/detail";
    }

    /**
     * 查询行为数据列表
     */
    @RequiresPermissions("system:behavior:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBehaviorData tBehaviorData) {
        // 清理空的时间参数，避免SQL错误
        Map<String, Object> params = tBehaviorData.getParams();
        if (params != null) {
            Object beginTimestamp = params.get("beginTimestamp");
            Object endTimestamp = params.get("endTimestamp");
            
            // 如果时间参数为空字符串或"??",则移除该参数
            if (beginTimestamp != null) {
                String beginStr = beginTimestamp.toString().trim();
                if ("".equals(beginStr) || "??".equals(beginStr) || "{}".equals(beginStr)) {
                    params.remove("beginTimestamp");
                }
            }
            if (endTimestamp != null) {
                String endStr = endTimestamp.toString().trim();
                if ("".equals(endStr) || "??".equals(endStr) || "{}".equals(endStr)) {
                    params.remove("endTimestamp");
                }
            }
        }
        
        startPage();
        List<TBehaviorDataVO> list = tBehaviorDataService.selectTBehaviorDataList(tBehaviorData);
        return getDataTable(list);
    }

    /**
     * 获取小鼠行为汇总信息
     */
    @Anonymous
    @GetMapping("/mouse/{mouseCode}/summary")
    @ResponseBody
    public AjaxResult getMouseBehaviorSummary(@PathVariable("mouseCode") String mouseCode) {
        try {
            Map<String, Object> summary = tBehaviorDataService.getMouseBehaviorSummary(mouseCode);
            return AjaxResult.success(summary);
        } catch (Exception e) {
            logger.error("获取小鼠行为汇总信息失败", e);
            return AjaxResult.error("获取行为汇总信息失败");
        }
    }

    /**
     * 获取小鼠行为趋势数据
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/mouse/{mouseCode}/trend")
    @ResponseBody
    public AjaxResult getMouseBehaviorTrend(@PathVariable("mouseCode") String mouseCode) {
        try {
            Map<String, Object> trendData = tBehaviorDataService.getMouseBehaviorTrend(mouseCode);
            return AjaxResult.success(trendData);
        } catch (Exception e) {
            logger.error("获取小鼠行为趋势数据失败", e);
            return AjaxResult.error("获取行为趋势数据失败");
        }
    }

    /**
     * 获取小鼠行为类型分布数据
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/mouse/{mouseCode}/distribution")
    @ResponseBody
    public AjaxResult getMouseBehaviorDistribution(@PathVariable("mouseCode") String mouseCode) {
        try {
            Map<String, Object> distributionData = tBehaviorDataService.getMouseBehaviorDistribution(mouseCode);
            return AjaxResult.success(distributionData);
        } catch (Exception e) {
            logger.error("获取小鼠行为分布数据失败", e);
            return AjaxResult.error("获取行为分布数据失败");
        }
    }

    /**
     * 获取小鼠行为统计数据
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/statistics/{mouseCode}")
    @ResponseBody
    public AjaxResult getMouseBehaviorStatistics(@PathVariable("mouseCode") String mouseCode) {
        try {
            Map<String, Object> statistics = tBehaviorDataService.getMouseBehaviorStatistics(mouseCode);
            return AjaxResult.success(statistics);
        } catch (Exception e) {
            logger.error("获取小鼠行为统计数据失败", e);
            return AjaxResult.error("获取统计数据失败");
        }
    }

    /**
     * 获取行为类型分布数据（支持时间范围查询）
     */
    @Anonymous
    @GetMapping("/type-distribution")
    @ResponseBody
    public AjaxResult getBehaviorTypeDistribution(
            @RequestParam(value = "mouseCode", required = false) String mouseCode,
            @RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(value = "hours", defaultValue = "24") Integer hours) {
        try {
            Map<String, Object> distribution = tBehaviorDataService.getBehaviorTypeDistribution(mouseCode, startTime, endTime, hours);
            return AjaxResult.success(distribution);
        } catch (Exception e) {
            logger.error("获取行为类型分布数据失败", e);
            return AjaxResult.error("获取行为类型分布数据失败: " + e.getMessage());
        }
    }

    /**
     * 获取小鼠最近行为记录
     */
    @Anonymous
    @GetMapping("/mouse/{mouseCode}/recent")
    @ResponseBody
    public AjaxResult getRecentBehaviors(@PathVariable("mouseCode") String mouseCode,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<TBehaviorDataVO> recentBehaviors = tBehaviorDataService.getRecentBehaviorsByMouseCode(mouseCode, pageSize);
            return AjaxResult.success(getDataTable(recentBehaviors));
        } catch (Exception e) {
            logger.error("获取小鼠最近行为记录失败", e);
            return AjaxResult.error("获取最近行为记录失败");
        }
    }

    /**
     * 获取行为类型列表
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/types")
    @ResponseBody
    public AjaxResult getBehaviorTypes() {
        try {
            // 使用枚举类获取行为类型
            Map<String, String> behaviorTypes = BehaviorTypeEnum.getAllCodeNameMap();
            return AjaxResult.success(behaviorTypes);
        } catch (Exception e) {
            logger.error("获取行为类型列表失败", e);
            return AjaxResult.error("获取行为类型列表失败");
        }
    }
    
    /**
     * 根据小鼠编码获取该小鼠的行为类型列表
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/types/{mouseCode}")
    @ResponseBody
    public AjaxResult getBehaviorTypesByMouse(@PathVariable("mouseCode") String mouseCode) {
        try {
            // 获取该小鼠的所有行为类型
            List<TBehaviorDataVO> behaviorList = tBehaviorDataService.selectTBehaviorDataList(new TBehaviorData() {{
                setMouseCode(mouseCode);
            }});
            
            // 提取唯一的行为类型
            Map<String, String> behaviorTypes = new HashMap<>();
            for (TBehaviorDataVO behavior : behaviorList) {
                String behaviorType = behavior.getBehaviorType();
                if (StringUtils.isNotEmpty(behaviorType)) {
                    String chineseName = BehaviorTypeEnum.getNameByCode(behaviorType);
                    behaviorTypes.put(behaviorType, chineseName);
                }
            }
            
            return AjaxResult.success(behaviorTypes);
        } catch (Exception e) {
            logger.error("获取小鼠行为类型列表失败", e);
            return AjaxResult.error("获取小鼠行为类型列表失败");
        }
    }

    /**
     * 获取行为记录详情
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult getBehaviorInfo(@PathVariable("id") Long id) {
        try {
            TBehaviorDataVO behaviorData = tBehaviorDataService.selectTBehaviorDataById(id);
            if (behaviorData == null) {
                return AjaxResult.error("行为记录不存在");
            }
            return AjaxResult.success(behaviorData);
        } catch (Exception e) {
            logger.error("获取行为记录详情失败", e);
            return AjaxResult.error("获取行为记录详情失败");
        }
    }

    /**
     * 获取同类型行为记录
     */
    @RequiresPermissions("system:behavior:view")
    @GetMapping("/similar/{behaviorType}")
    @ResponseBody
    public AjaxResult getSimilarBehaviors(@PathVariable("behaviorType") String behaviorType,
                                        @RequestParam(value = "mouseCode", required = false) String mouseCode,
                                        @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        try {
            // 构建查询条件
            TBehaviorData queryParam = new TBehaviorData();
            queryParam.setBehaviorType(behaviorType);
            if (StringUtils.isNotEmpty(mouseCode)) {
                queryParam.setMouseCode(mouseCode);
            }
            
            // 设置分页参数
            startPage();
            
            // 获取同类型行为记录
            List<TBehaviorDataVO> similarBehaviors = tBehaviorDataService.selectTBehaviorDataList(queryParam);
            
            return AjaxResult.success(similarBehaviors);
        } catch (Exception e) {
            logger.error("获取同类型行为记录失败", e);
            return AjaxResult.error("获取同类型行为记录失败");
        }
    }

}