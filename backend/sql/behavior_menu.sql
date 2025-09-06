-- 行为管理菜单 SQL
-- 主菜单
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为管理', '0', '11', '/system/behavior', 'C', '0', 'system:behavior:view', 'fa fa-line-chart', 'admin', sysdate(), '', null, '行为管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为查询', @parentId, '1',  '#',  'F', '0', 'system:behavior:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为新增', @parentId, '2',  '#',  'F', '0', 'system:behavior:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为修改', @parentId, '3',  '#',  'F', '0', 'system:behavior:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为删除', @parentId, '4',  '#',  'F', '0', 'system:behavior:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为导出', @parentId, '5',  '#',  'F', '0', 'system:behavior:export',       '#', 'admin', sysdate(), '', null, '');

-- 小鼠行为详情页面菜单
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠行为详情', @parentId, '6',  '/system/behavior/mouse-behavior',  'C', '0', 'system:behavior:mouse:view',    '#', 'admin', sysdate(), '', null, '小鼠行为详情页面');

-- 行为记录详情页面菜单
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('行为记录详情', @parentId, '7',  '/system/behavior/detail',  'C', '0', 'system:behavior:detail:view',    '#', 'admin', sysdate(), '', null, '行为记录详情页面');