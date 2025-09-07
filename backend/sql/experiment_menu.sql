-- ===========================
-- 实验管理系统菜单配置
-- 创建时间: 2025-01-17
-- 描述: 实验管理模块的菜单项配置
-- ===========================

-- ----------------------------
-- 实验管理主菜单
-- ----------------------------
INSERT INTO sys_menu VALUES('2100', '实验管理', '0', '4', 'experiment', NULL, NULL, '1', '0', 'M', '0', '0', '', 'experiment', 'admin', NOW(), '', NULL, '实验管理目录');

-- ----------------------------
-- 实验管理子菜单
-- ----------------------------

-- 实验列表管理
INSERT INTO sys_menu VALUES('2101', '实验列表', '2100', '1', 'experiment', 'system/experiment/experiment', NULL, '1', '0', 'C', '0', '0', 'system:experiment:view', 'experiment', 'admin', NOW(), '', NULL, '实验列表菜单');

-- 创建实验
INSERT INTO sys_menu VALUES('2102', '创建实验', '2100', '2', 'add', 'system/experiment/add', NULL, '1', '0', 'C', '0', '0', 'system:experiment:add', 'plus', 'admin', NOW(), '', NULL, '创建实验菜单');

-- 实验模板管理
INSERT INTO sys_menu VALUES('2103', '实验模板', '2100', '3', 'template', 'system/experiment/template', NULL, '1', '0', 'C', '0', '0', 'system:experiment:template', 'form', 'admin', NOW(), '', NULL, '实验模板管理菜单');

-- 实验详情
INSERT INTO sys_menu VALUES('2104', '实验详情', '2100', '4', 'detail', 'system/experiment/detail', NULL, '1', '0', 'C', '1', '0', 'system:experiment:detail', 'eye', 'admin', NOW(), '', NULL, '实验详情菜单');

-- 实验二维码管理
INSERT INTO sys_menu VALUES('2105', '二维码管理', '2100', '5', 'qrcode', 'system/experiment/qrcode', NULL, '1', '0', 'C', '0', '0', 'system:experiment:qrcode', 'qrcode', 'admin', NOW(), '', NULL, '实验二维码管理菜单');

-- 实验监控大屏
INSERT INTO sys_menu VALUES('2106', '监控大屏', '2100', '6', 'monitor', 'system/experiment/monitor', NULL, '1', '0', 'C', '0', '0', 'system:experiment:monitor', 'monitor', 'admin', NOW(), '', NULL, '实验监控大屏菜单');

-- ----------------------------
-- 笼子管理主菜单
-- ----------------------------
INSERT INTO sys_menu VALUES('2200', '笼子管理', '0', '5', 'cage', NULL, NULL, '1', '0', 'M', '0', '0', '', 'cage', 'admin', NOW(), '', NULL, '笼子管理目录');

-- ----------------------------
-- 笼子管理子菜单
-- ----------------------------

-- 笼子列表管理
INSERT INTO sys_menu VALUES('2201', '笼子列表', '2200', '1', 'cage', 'system/cage/cage', NULL, '1', '0', 'C', '0', '0', 'system:cage:view', 'cage', 'admin', NOW(), '', NULL, '笼子列表菜单');

-- 添加笼子
INSERT INTO sys_menu VALUES('2202', '添加笼子', '2200', '2', 'add', 'system/cage/add', NULL, '1', '0', 'C', '0', '0', 'system:cage:add', 'plus', 'admin', NOW(), '', NULL, '添加笼子菜单');

-- 笼子绑定管理
INSERT INTO sys_menu VALUES('2203', '绑定管理', '2200', '3', 'binding', 'system/cage/binding', NULL, '1', '0', 'C', '0', '0', 'system:cage:binding', 'link', 'admin', NOW(), '', NULL, '笼子绑定管理菜单');

-- 二维码扫描中心
INSERT INTO sys_menu VALUES('2204', '扫描中心', '2200', '4', 'scan', 'system/cage/qrcode-scan', NULL, '1', '0', 'C', '0', '0', 'system:cage:scan', 'scan', 'admin', NOW(), '', NULL, '二维码扫描中心菜单');

-- 笼子状态监控
INSERT INTO sys_menu VALUES('2205', '状态监控', '2200', '5', 'status', 'system/cage/status', NULL, '1', '0', 'C', '0', '0', 'system:cage:status', 'monitor', 'admin', NOW(), '', NULL, '笼子状态监控菜单');

-- ----------------------------
-- 实验管理按钮权限
-- ----------------------------

-- 实验列表按钮权限
INSERT INTO sys_menu VALUES('21011', '实验查询', '2101', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:query', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21012', '实验新增', '2101', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:add', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21013', '实验修改', '2101', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21014', '实验删除', '2101', '4', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21015', '实验导出', '2101', '5', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:export', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21016', '实验启动', '2101', '6', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:start', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21017', '实验暂停', '2101', '7', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:pause', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21018', '实验结束', '2101', '8', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:finish', '#', 'admin', NOW(), '', NULL, '');

-- 实验模板按钮权限
INSERT INTO sys_menu VALUES('21031', '模板查询', '2103', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:template:query', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21032', '模板新增', '2103', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:template:add', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21033', '模板修改', '2103', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:template:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21034', '模板删除', '2103', '4', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:template:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21035', '模板导出', '2103', '5', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:template:export', '#', 'admin', NOW(), '', NULL, '');

-- 二维码管理按钮权限
INSERT INTO sys_menu VALUES('21051', '二维码查询', '2105', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:qrcode:query', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21052', '二维码生成', '2105', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:qrcode:generate', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21053', '二维码下载', '2105', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:qrcode:download', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('21054', '二维码打印', '2105', '4', '', '', NULL, '1', '0', 'F', '0', '0', 'system:experiment:qrcode:print', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 笼子管理按钮权限
-- ----------------------------

-- 笼子列表按钮权限
INSERT INTO sys_menu VALUES('22011', '笼子查询', '2201', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:query', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22012', '笼子新增', '2201', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:add', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22013', '笼子修改', '2201', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:edit', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22014', '笼子删除', '2201', '4', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:remove', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22015', '笼子导出', '2201', '5', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:export', '#', 'admin', NOW(), '', NULL, '');

-- 笼子绑定按钮权限
INSERT INTO sys_menu VALUES('22031', '绑定查询', '2203', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:binding:query', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22032', '绑定实验', '2203', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:binding:bind', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22033', '解绑实验', '2203', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:binding:unbind', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22034', '批量绑定', '2203', '4', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:binding:batch', '#', 'admin', NOW(), '', NULL, '');

-- 扫描中心按钮权限
INSERT INTO sys_menu VALUES('22041', '扫描权限', '2204', '1', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:scan:permission', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22042', '扫描绑定', '2204', '2', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:scan:bind', '#', 'admin', NOW(), '', NULL, '');
INSERT INTO sys_menu VALUES('22043', '扫描历史', '2204', '3', '', '', NULL, '1', '0', 'F', '0', '0', 'system:cage:scan:history', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 角色菜单关联（为管理员角色分配所有实验管理权限）
-- ----------------------------

-- 实验管理权限
INSERT INTO sys_role_menu VALUES ('1', '2100');
INSERT INTO sys_role_menu VALUES ('1', '2101');
INSERT INTO sys_role_menu VALUES ('1', '2102');
INSERT INTO sys_role_menu VALUES ('1', '2103');
INSERT INTO sys_role_menu VALUES ('1', '2104');
INSERT INTO sys_role_menu VALUES ('1', '2105');
INSERT INTO sys_role_menu VALUES ('1', '2106');

-- 笼子管理权限
INSERT INTO sys_role_menu VALUES ('1', '2200');
INSERT INTO sys_role_menu VALUES ('1', '2201');
INSERT INTO sys_role_menu VALUES ('1', '2202');
INSERT INTO sys_role_menu VALUES ('1', '2203');
INSERT INTO sys_role_menu VALUES ('1', '2204');
INSERT INTO sys_role_menu VALUES ('1', '2205');

-- 实验管理按钮权限
INSERT INTO sys_role_menu VALUES ('1', '21011');
INSERT INTO sys_role_menu VALUES ('1', '21012');
INSERT INTO sys_role_menu VALUES ('1', '21013');
INSERT INTO sys_role_menu VALUES ('1', '21014');
INSERT INTO sys_role_menu VALUES ('1', '21015');
INSERT INTO sys_role_menu VALUES ('1', '21016');
INSERT INTO sys_role_menu VALUES ('1', '21017');
INSERT INTO sys_role_menu VALUES ('1', '21018');

-- 实验模板按钮权限
INSERT INTO sys_role_menu VALUES ('1', '21031');
INSERT INTO sys_role_menu VALUES ('1', '21032');
INSERT INTO sys_role_menu VALUES ('1', '21033');
INSERT INTO sys_role_menu VALUES ('1', '21034');
INSERT INTO sys_role_menu VALUES ('1', '21035');

-- 二维码管理按钮权限
INSERT INTO sys_role_menu VALUES ('1', '21051');
INSERT INTO sys_role_menu VALUES ('1', '21052');
INSERT INTO sys_role_menu VALUES ('1', '21053');
INSERT INTO sys_role_menu VALUES ('1', '21054');

-- 笼子管理按钮权限
INSERT INTO sys_role_menu VALUES ('1', '22011');
INSERT INTO sys_role_menu VALUES ('1', '22012');
INSERT INTO sys_role_menu VALUES ('1', '22013');
INSERT INTO sys_role_menu VALUES ('1', '22014');
INSERT INTO sys_role_menu VALUES ('1', '22015');

-- 笼子绑定按钮权限
INSERT INTO sys_role_menu VALUES ('1', '22031');
INSERT INTO sys_role_menu VALUES ('1', '22032');
INSERT INTO sys_role_menu VALUES ('1', '22033');
INSERT INTO sys_role_menu VALUES ('1', '22034');

-- 扫描中心按钮权限
INSERT INTO sys_role_menu VALUES ('1', '22041');
INSERT INTO sys_role_menu VALUES ('1', '22042');
INSERT INTO sys_role_menu VALUES ('1', '22043');

-- ===========================
-- 菜单配置完成
-- 包含以下功能模块：
-- 1. 实验管理 - 实验列表、创建实验、实验模板、实验详情、二维码管理、监控大屏
-- 2. 笼子管理 - 笼子列表、添加笼子、绑定管理、扫描中心、状态监控
-- 3. 完整的按钮权限配置
-- 4. 管理员角色权限分配
-- ===========================