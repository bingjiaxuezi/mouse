# MyBatis-Plus 改造方案

## 项目现状分析

当前项目使用传统的 MyBatis 框架，所有的 Service 实现类都直接依赖 Mapper 接口，需要手写 SQL 语句。为了提高开发效率和代码质量，建议将项目升级到 MyBatis-Plus。

### 当前架构问题
1. **手写SQL繁琐**：所有CRUD操作都需要在XML中手写SQL
2. **代码重复**：每个Service都有相似的基础CRUD方法
3. **维护成本高**：SQL语句分散在各个XML文件中，难以统一管理
4. **开发效率低**：简单的增删改查操作需要大量样板代码

## MyBatis-Plus 优势

1. **内置通用CRUD**：提供BaseMapper接口，包含常用的增删改查方法
2. **代码生成器**：可以自动生成Entity、Mapper、Service等代码
3. **条件构造器**：提供链式调用的查询条件构造
4. **分页插件**：内置分页功能，使用简单
5. **性能分析**：提供SQL性能分析插件
6. **逻辑删除**：支持逻辑删除功能

## 改造方案

### 第一阶段：添加MyBatis-Plus依赖

1. **修改主pom.xml**，添加MyBatis-Plus版本管理：
```xml
<mybatis-plus.version>3.5.3.1</mybatis-plus.version>
```

2. **在ruoyi-common模块添加依赖**：
```xml
<!-- MyBatis-Plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>
```

### 第二阶段：配置MyBatis-Plus

1. **修改application.yml配置**：
```yaml
# MyBatis-Plus配置
mybatis-plus:
  # 搜索指定包别名
  type-aliases-package: com.ruoyi.**.domain
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations: classpath*:mapper/**/*Mapper.xml
  # 全局配置
  global-config:
    # 数据库相关配置
    db-config:
      # 主键类型（AUTO:"数据库ID自增", INPUT:"用户输入ID", ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID"）
      id-type: AUTO
      # 字段策略（IGNORED:"忽略判断", NOT_NULL:"非NULL判断", NOT_EMPTY:"非空判断"）
      field-strategy: NOT_EMPTY
      # 数据库大写下划线转换
      table-underline: true
      # 逻辑删除配置
      logic-delete-field: delFlag
      logic-delete-value: 2
      logic-not-delete-value: 0
  # 原生配置
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: false
```

2. **创建MyBatis-Plus配置类**：
```java
@Configuration
@MapperScan("com.ruoyi.**.mapper")
public class MybatisPlusConfig {
    
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

### 第三阶段：改造Mapper接口

将现有的Mapper接口继承BaseMapper：

```java
// 改造前
public interface TMouseMapper {
    List<TMouseVO> selectTMouseList(TMouse tMouse);
    // 其他方法...
}

// 改造后
public interface TMouseMapper extends BaseMapper<TMouse> {
    List<TMouseVO> selectTMouseList(TMouse tMouse);
    // 其他复杂查询方法保留
}
```

### 第四阶段：改造Service层

#### 4.1 创建基础Service接口

```java
public interface IBaseService<T> extends IService<T> {
    // 可以添加项目特有的通用方法
}
```

#### 4.2 改造Service实现类

```java
// 改造前
@Service
public class TMouseServiceImpl implements ITMouseService {
    @Autowired
    private TMouseMapper tMouseMapper;
    
    @Override
    public List<TMouseVO> selectTMouseList(TMouse tMouse) {
        return tMouseMapper.selectTMouseList(tMouse);
    }
    
    @Override
    public int insertTMouse(TMouseDTO tMouseDTO) {
        TMouse tMouse = new TMouse();
        BeanUtils.copyProperties(tMouseDTO, tMouse);
        tMouse.setCreateTime(DateUtils.getNowDate());
        return tMouseMapper.insertTMouse(tMouse);
    }
    // 其他CRUD方法...
}

// 改造后
@Service
public class TMouseServiceImpl extends ServiceImpl<TMouseMapper, TMouse> implements ITMouseService {
    
    @Override
    public List<TMouseVO> selectTMouseList(TMouse tMouse) {
        return baseMapper.selectTMouseList(tMouse);
    }
    
    @Override
    public int insertTMouse(TMouseDTO tMouseDTO) {
        TMouse tMouse = new TMouse();
        BeanUtils.copyProperties(tMouseDTO, tMouse);
        tMouse.setCreateTime(DateUtils.getNowDate());
        return baseMapper.insert(tMouse); // 使用MyBatis-Plus的insert方法
    }
    
    // 可以直接使用继承来的方法：
    // save(), saveOrUpdate(), removeById(), updateById(), getById(), list(), page() 等
}
```

#### 4.3 Service接口改造

```java
// 改造后的Service接口
public interface ITMouseService extends IService<TMouse> {
    List<TMouseVO> selectTMouseList(TMouse tMouse);
    int insertTMouse(TMouseDTO tMouseDTO);
    int updateTMouse(TMouseDTO tMouseDTO);
    String checkMouseCodeUnique(String mouseCode);
    TMouseVO selectTMouseByMouseCode(String mouseCode);
}
```

### 第五阶段：Controller层调整

利用MyBatis-Plus提供的通用方法简化Controller：

```java
@RestController
@RequestMapping("/system/mouse")
public class TMouseController extends BaseController {
    
    @Autowired
    private ITMouseService tMouseService;
    
    /**
     * 查询老鼠列表
     */
    @PostMapping("/list")
    public TableDataInfo list(TMouse tMouse) {
        startPage();
        List<TMouseVO> list = tMouseService.selectTMouseList(tMouse);
        return getDataTable(list);
    }
    
    /**
     * 获取老鼠详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tMouseService.getById(id)); // 使用MyBatis-Plus方法
    }
    
    /**
     * 新增老鼠
     */
    @PostMapping
    public AjaxResult add(@RequestBody TMouseDTO tMouseDTO) {
        return toAjax(tMouseService.insertTMouse(tMouseDTO));
    }
    
    /**
     * 修改老鼠
     */
    @PutMapping
    public AjaxResult edit(@RequestBody TMouseDTO tMouseDTO) {
        return toAjax(tMouseService.updateTMouse(tMouseDTO));
    }
    
    /**
     * 删除老鼠
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tMouseService.removeByIds(Arrays.asList(ids))); // 使用MyBatis-Plus方法
    }
}
```

## 改造收益

### 1. 代码量减少
- 基础CRUD操作无需手写SQL
- Service层代码量减少约60%
- Mapper XML文件大幅简化

### 2. 开发效率提升
- 新增实体只需继承BaseMapper即可获得完整CRUD功能
- 复杂查询可以使用QueryWrapper构造
- 分页查询更加简单

### 3. 代码质量提升
- 统一的编码规范
- 减少SQL语法错误
- 更好的类型安全

### 4. 维护成本降低
- 通用方法统一维护
- SQL优化集中处理
- 更容易进行单元测试

## 实施计划

### 第一步：环境准备（1天）
1. 添加MyBatis-Plus依赖
2. 配置MyBatis-Plus
3. 创建配置类

### 第二步：逐步改造（3-5天）
1. 改造TMouseServiceImpl（示例）
2. 改造其他Service实现类
3. 更新Controller层调用

### 第三步：测试验证（2天）
1. 单元测试
2. 集成测试
3. 功能验证

### 第四步：文档更新（1天）
1. 更新开发文档
2. 编写最佳实践指南

## 注意事项

1. **兼容性**：MyBatis-Plus与原有MyBatis完全兼容，可以逐步迁移
2. **性能**：MyBatis-Plus的通用方法经过优化，性能不会下降
3. **学习成本**：团队需要学习MyBatis-Plus的API和最佳实践
4. **复杂查询**：对于复杂的关联查询，仍然可以保留原有的XML方式

## 总结

通过引入MyBatis-Plus，可以显著提高开发效率，减少样板代码，提升代码质量。建议采用渐进式改造方式，先改造一个模块作为示例，验证效果后再全面推广。