-- 小鼠管理菜单 SQL
-- 主菜单
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠管理', '0', '10', '/system/mouse', 'C', '0', 'system:mouse:view', 'fa fa-paw', 'admin', sysdate(), '', null, '小鼠管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠查询', @parentId, '1',  '#',  'F', '0', 'system:mouse:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠新增', @parentId, '2',  '#',  'F', '0', 'system:mouse:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠修改', @parentId, '3',  '#',  'F', '0', 'system:mouse:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠删除', @parentId, '4',  '#',  'F', '0', 'system:mouse:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小鼠导出', @parentId, '5',  '#',  'F', '0', 'system:mouse:export',       '#', 'admin', sysdate(), '', null, '');