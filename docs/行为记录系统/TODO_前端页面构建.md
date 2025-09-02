# 行为记录系统前端页面构建 - 待办事项清单

## 数据库配置

### 1. 执行菜单SQL脚本
```sql
-- 执行以下SQL文件添加行为管理菜单
source d:\mouse\backend\sql\behavior_menu.sql;
```

### 2. 验证菜单数据
```sql
-- 查询验证菜单是否正确添加
SELECT * FROM sys_menu WHERE menu_name LIKE '%行为%';
```

## 后端接口开发

### 1. Controller层接口
需要创建 `BehaviorController.java`，实现以下接口：

```java
// 行为记录列表查询
@PostMapping("/list")
public TableDataInfo list(BehaviorRecord behaviorRecord)

// 行为统计数据
@GetMapping("/statistics")
public AjaxResult getStatistics()

// 小鼠行为详情
@GetMapping("/mouse/{mouseId}")
public AjaxResult getMouseBehavior(@PathVariable Long mouseId)

// 行为记录详情
@GetMapping("/{id}")
public AjaxResult getInfo(@PathVariable Long id)

// 行为类型选项
@GetMapping("/behaviorTypes")
public AjaxResult getBehaviorTypes()

// 导出功能
@PostMapping("/export")
public AjaxResult export(BehaviorRecord behaviorRecord)
```

### 2. Service层服务
需要实现 `IBehaviorRecordService` 接口和 `BehaviorRecordServiceImpl` 实现类。

### 3. Mapper层数据访问
需要创建 `BehaviorRecordMapper.java` 和对应的XML映射文件。

## 权限配置

### 1. 角色权限分配
在系统管理 -> 角色管理中，为相关角色分配行为管理权限：
- `system:behavior:view` - 行为管理查看
- `system:behavior:list` - 行为查询
- `system:behavior:add` - 行为新增
- `system:behavior:edit` - 行为修改
- `system:behavior:remove` - 行为删除
- `system:behavior:export` - 行为导出
- `system:behavior:mouse:view` - 小鼠行为详情
- `system:behavior:detail:view` - 行为记录详情

### 2. 用户权限验证
确保相关用户具有访问行为管理功能的权限。

## 静态资源配置

### 1. JavaScript文件引用
确保在相关页面中正确引用 `behavior.js`：
```html
<script th:src="@{/ruoyi/js/behavior.js}"></script>
```

### 2. CSS样式检查
验证页面样式是否正常显示，特别是：
- Bootstrap样式
- 图表容器样式
- 响应式布局

## 数据字典配置

### 1. 行为类型字典
在系统管理 -> 字典管理中添加行为类型字典：
```
字典名称：行为类型
字典类型：behavior_type
字典数据：
- 1: 进食
- 2: 饮水
- 3: 运动
- 4: 休息
- 5: 探索
- 6: 社交
- 7: 其他
```

### 2. 行为强度字典
```
字典名称：行为强度
字典类型：behavior_intensity
字典数据：
- 1: 低
- 2: 中
- 3: 高
```

## 测试验证

### 1. 页面访问测试
- 访问 `/system/behavior` 确认主页面正常显示
- 访问 `/system/behavior/mouse-behavior?mouseId=1` 确认小鼠行为详情页正常
- 访问 `/system/behavior/detail?id=1` 确认行为记录详情页正常

### 2. 功能测试
- 测试查询筛选功能
- 测试图表显示功能
- 测试页面跳转功能
- 测试响应式布局

### 3. 权限测试
- 测试不同角色的权限控制
- 测试按钮权限显示/隐藏

## 部署配置

### 1. 项目构建
使用项目提供的部署脚本：
```powershell
PowerShell -ExecutionPolicy Bypass -File "D:\mouse\scripts\deploy.ps1"
```

### 2. 服务启动
在项目根目录执行：
```bash
docker-compose up -d
```

## 注意事项

### 1. 数据库连接
确保数据库连接配置正确，特别是行为记录相关表的存在。

### 2. 接口联调
前端页面的AJAX请求需要与后端接口的参数和返回格式保持一致。

### 3. 错误处理
确保前端页面有适当的错误处理和用户提示。

### 4. 性能优化
对于大量数据的查询和图表渲染，需要考虑分页和性能优化。

## 联系支持

如果在配置过程中遇到问题，请检查：
1. 数据库表结构是否正确
2. 后端接口是否已实现
3. 权限配置是否正确
4. 静态资源是否正确加载

建议按照上述清单逐项完成配置，确保行为记录系统前端功能的完整可用。