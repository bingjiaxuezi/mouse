# 行为类型分布图表需求对齐文档

## 项目上下文分析

### 现有项目架构
- **后端框架**: Spring Boot + MyBatis + RuoYi框架
- **前端技术**: Thymeleaf模板 + Bootstrap + jQuery + ECharts图表库
- **数据库**: MySQL，主要表为 `t_behavior_data`
- **现有统计接口**: `/system/behavior/statistics` 已存在基础统计功能

### 现有行为数据模型
```sql
t_behavior_data 表结构:
- id: 主键
- mouse_code: 小鼠编号
- device_code: 设备编号
- experiment_id: 实验ID
- behavior_type: 行为类型代码
- behavior_value: 行为值
- timestamp: 记录时间
- duration: 持续时间
- create_time: 创建时间
```

### 现有前端实现分析
- 位置: `/system/behavior/behavior.html`
- 当前图表区域显示: "暂无行为类型数据"
- 使用ECharts库进行图表渲染
- 已有基础的统计数据加载框架

## 原始需求分析

用户希望:
1. **专门的后端接口**: 创建专门用于行为类型分布统计的API接口
2. **时间范围支持**: 
   - 默认显示近24小时的数据
   - 前端可选择自定义时间范围
3. **数据分析**: 后端进行数据统计分析，返回适合前端图表渲染的格式
4. **饼状图展示**: 以饼状图形式展示不同行为类型的分布情况

## 需求理解确认

### 功能需求
1. **后端API设计**:
   - 接口路径: `/system/behavior/typeDistribution`
   - 支持参数: `startTime`, `endTime` (可选，默认近24小时)
   - 返回格式: 适合ECharts饼状图的数据结构

2. **数据统计逻辑**:
   - 按行为类型分组统计数量
   - 计算各类型占比
   - 支持时间范围过滤

3. **前端功能**:
   - 时间范围选择器
   - ECharts饼状图渲染
   - 数据刷新机制

### 技术约束
- 必须与现有RuoYi框架架构保持一致
- 复用现有的权限控制和异常处理机制
- 保持与现有代码风格一致
- 使用现有的ECharts图表库

### 边界确认
- **包含**: 行为类型分布统计API、时间范围选择、饼状图展示
- **不包含**: 其他类型的统计图表、导出功能、实时数据推送

## 疑问澄清

### 已明确的设计决策
1. **时间范围默认值**: 近24小时
2. **图表类型**: 饼状图
3. **数据来源**: t_behavior_data表
4. **接口风格**: RESTful API

### 需要确认的问题
1. **行为类型映射**: 是否需要将behavior_type代码转换为可读的中文名称？
2. **数据过滤**: 是否需要按实验ID或设备进行额外过滤？
3. **缓存策略**: 是否需要对统计结果进行缓存优化？
4. **权限控制**: 是否需要特殊的权限验证？

## 验收标准

1. **后端接口**:
   - 接口响应时间 < 2秒
   - 支持时间范围参数
   - 返回标准的RuoYi响应格式
   - 包含完整的异常处理

2. **前端功能**:
   - 饼状图正确渲染
   - 时间选择器功能正常
   - 数据刷新无异常
   - 响应式设计适配

3. **数据准确性**:
   - 统计数据与数据库实际数据一致
   - 百分比计算准确
   - 时间范围过滤正确

## 技术实现方案预览

### 后端实现
- Controller: `BehaviorController.typeDistribution()`
- Service: `IBehaviorDataService.getTypeDistribution()`
- Mapper: `TBehaviorDataMapper.selectTypeDistribution()`

### 前端实现
- 时间选择组件集成
- ECharts配置优化
- AJAX数据加载

## 项目集成方案

- 扩展现有的BehaviorController
- 复用现有的Service和Mapper层
- 在现有的behavior.html页面中集成新功能
- 保持与现有统计功能的一致性