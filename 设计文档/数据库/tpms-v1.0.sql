/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/8/23 16:38:46                           */
/*==============================================================*/


drop table if exists menu;

drop table if exists menu_permission;

drop table if exists permission;

drop table if exists resource;

drop table if exists resource_action;

drop table if exists role;

drop table if exists role_permission;

drop table if exists scope;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   bigint not null auto_increment comment '菜单标识符',
   parent_id            bigint comment '父标识符',
   parent_path          varchar(200) comment '父菜单路径',
   level                int not null comment '层级（从0开始）',
   name                 varchar(80) not null comment '名称',
   url                  varchar(200) comment '菜单url',
   class_name           varchar(100) comment '菜单样式名称',
   system_name          varchar(40) not null comment '系统名称',
   description          varchar(200) comment '描述',
   display_order        int not null comment '显示顺序',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   primary key (id)
);

alter table menu comment '菜单';

/*==============================================================*/
/* Table: menu_permission                                       */
/*==============================================================*/
create table menu_permission
(
   id                   bigint not null auto_increment comment '菜单权限关系标识符',
   menu_id              bigint not null comment '菜单标识符',
   permission_id        bigint not null comment '权限标识符',
   primary key (id)
);

alter table menu_permission comment '菜单权限关系';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   bigint not null auto_increment comment '权限标识符',
   resource_id          bigint not null comment '资源标识符',
   action_id            bigint not null comment '资源动作标识符',
   primary key (id)
);

alter table permission comment '权限';

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   id                   bigint not null auto_increment comment '资源标识符',
   parent_id            bigint comment '父标识符',
   name                 varchar(80) not null comment '名称',
   system_name          varchar(40) not null comment '系统名称',
   description          varchar(200) comment '描述',
   display_order        int not null comment '显示顺序',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   primary key (id)
);

alter table resource comment '资源';

/*==============================================================*/
/* Table: resource_action                                       */
/*==============================================================*/
create table resource_action
(
   id                   bigint not null auto_increment comment '资源动作标识符',
   resource_id          bigint comment '资源标识符',
   name                 varchar(80) not null comment '名称',
   system_name          varchar(40) not null comment '系统名称',
   description          varchar(200) comment '描述',
   display_order        int not null comment '显示顺序',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   primary key (id)
);

alter table resource_action comment '资源动作';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   bigint not null auto_increment comment '角色标识符',
   scope_id             bigint not null comment '作用域标识符',
   name                 varchar(80) not null comment '名称',
   system_name          varchar(40) not null comment '系统名称',
   description          varchar(200) comment '描述',
   display_order        int not null comment '显示顺序',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   primary key (id)
);

alter table role comment '角色';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   bigint not null auto_increment comment '角色权限关系标识符',
   role_id              bigint not null comment '角色标识符',
   permission_id        bigint not null comment '权限标识符',
   primary key (id)
);

alter table role_permission comment '角色权限关系';

/*==============================================================*/
/* Table: scope                                                 */
/*==============================================================*/
create table scope
(
   id                   bigint not null auto_increment comment '作用域标识符',
   parent_id            bigint comment '父标识符',
   name                 varchar(80) not null comment '名称',
   system_name          varchar(40) not null comment '系统名称',
   description          varchar(200) comment '描述',
   display_order        int not null comment '显示顺序',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   primary key (id)
);

alter table scope comment '作用域';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment comment '用户标识符',
   username             varchar(80) not null comment '用户名',
   system_name          varchar(40) comment '系统名称',
   full_name            varchar(80) comment '真实名称',
   password             varchar(80) not null comment '密码',
   password_salt        varchar(20) not null comment '密码盐',
   activated            bit not null comment '已激活',
   enabled              bit not null comment '已启用',
   deleted              bit not null comment '已删除',
   create_time          datetime not null comment '创建时间',
   creator              varchar(80) not null comment '创建人',
   modify_time          datetime not null comment '修改时间',
   modifier             varchar(80) not null comment '修改人',
   access_time          datetime comment '访问时间',
   access_ip            varchar(40) comment '访问ip地址',
   primary key (id)
);

alter table user comment '用户';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   bigint not null auto_increment comment '用户角色关系标识符',
   user_id              bigint not null comment '用户标识符',
   role_id              bigint not null comment '角色标识符',
   primary key (id)
);

alter table user_role comment '用户角色关系';

alter table menu add constraint FK_Reference_13 foreign key (parent_id)
      references menu (id) on delete restrict on update restrict;

alter table menu_permission add constraint FK_Reference_10 foreign key (menu_id)
      references menu (id) on delete restrict on update restrict;

alter table menu_permission add constraint FK_Reference_11 foreign key (permission_id)
      references permission (id) on delete restrict on update restrict;

alter table permission add constraint FK_Reference_5 foreign key (resource_id)
      references resource (id) on delete restrict on update restrict;

alter table permission add constraint FK_Reference_6 foreign key (action_id)
      references resource_action (id) on delete restrict on update restrict;

alter table resource add constraint FK_Reference_3 foreign key (parent_id)
      references resource (id) on delete restrict on update restrict;

alter table resource_action add constraint FK_Reference_4 foreign key (resource_id)
      references resource (id) on delete restrict on update restrict;

alter table role add constraint FK_Reference_7 foreign key (scope_id)
      references scope (id) on delete restrict on update restrict;

alter table role_permission add constraint FK_Reference_8 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

alter table role_permission add constraint FK_Reference_9 foreign key (permission_id)
      references permission (id) on delete restrict on update restrict;

alter table scope add constraint FK_Reference_12 foreign key (parent_id)
      references scope (id) on delete restrict on update restrict;

alter table user_role add constraint FK_Reference_1 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_role add constraint FK_Reference_2 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

