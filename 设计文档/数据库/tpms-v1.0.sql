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
   id                   bigint not null auto_increment comment '�˵���ʶ��',
   parent_id            bigint comment '����ʶ��',
   parent_path          varchar(200) comment '���˵�·��',
   level                int not null comment '�㼶����0��ʼ��',
   name                 varchar(80) not null comment '����',
   url                  varchar(200) comment '�˵�url',
   class_name           varchar(100) comment '�˵���ʽ����',
   system_name          varchar(40) not null comment 'ϵͳ����',
   description          varchar(200) comment '����',
   display_order        int not null comment '��ʾ˳��',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   primary key (id)
);

alter table menu comment '�˵�';

/*==============================================================*/
/* Table: menu_permission                                       */
/*==============================================================*/
create table menu_permission
(
   id                   bigint not null auto_increment comment '�˵�Ȩ�޹�ϵ��ʶ��',
   menu_id              bigint not null comment '�˵���ʶ��',
   permission_id        bigint not null comment 'Ȩ�ޱ�ʶ��',
   primary key (id)
);

alter table menu_permission comment '�˵�Ȩ�޹�ϵ';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   bigint not null auto_increment comment 'Ȩ�ޱ�ʶ��',
   resource_id          bigint not null comment '��Դ��ʶ��',
   action_id            bigint not null comment '��Դ������ʶ��',
   primary key (id)
);

alter table permission comment 'Ȩ��';

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   id                   bigint not null auto_increment comment '��Դ��ʶ��',
   parent_id            bigint comment '����ʶ��',
   name                 varchar(80) not null comment '����',
   system_name          varchar(40) not null comment 'ϵͳ����',
   description          varchar(200) comment '����',
   display_order        int not null comment '��ʾ˳��',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   primary key (id)
);

alter table resource comment '��Դ';

/*==============================================================*/
/* Table: resource_action                                       */
/*==============================================================*/
create table resource_action
(
   id                   bigint not null auto_increment comment '��Դ������ʶ��',
   resource_id          bigint comment '��Դ��ʶ��',
   name                 varchar(80) not null comment '����',
   system_name          varchar(40) not null comment 'ϵͳ����',
   description          varchar(200) comment '����',
   display_order        int not null comment '��ʾ˳��',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   primary key (id)
);

alter table resource_action comment '��Դ����';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   bigint not null auto_increment comment '��ɫ��ʶ��',
   scope_id             bigint not null comment '�������ʶ��',
   name                 varchar(80) not null comment '����',
   system_name          varchar(40) not null comment 'ϵͳ����',
   description          varchar(200) comment '����',
   display_order        int not null comment '��ʾ˳��',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   primary key (id)
);

alter table role comment '��ɫ';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   bigint not null auto_increment comment '��ɫȨ�޹�ϵ��ʶ��',
   role_id              bigint not null comment '��ɫ��ʶ��',
   permission_id        bigint not null comment 'Ȩ�ޱ�ʶ��',
   primary key (id)
);

alter table role_permission comment '��ɫȨ�޹�ϵ';

/*==============================================================*/
/* Table: scope                                                 */
/*==============================================================*/
create table scope
(
   id                   bigint not null auto_increment comment '�������ʶ��',
   parent_id            bigint comment '����ʶ��',
   name                 varchar(80) not null comment '����',
   system_name          varchar(40) not null comment 'ϵͳ����',
   description          varchar(200) comment '����',
   display_order        int not null comment '��ʾ˳��',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   primary key (id)
);

alter table scope comment '������';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment comment '�û���ʶ��',
   username             varchar(80) not null comment '�û���',
   system_name          varchar(40) comment 'ϵͳ����',
   full_name            varchar(80) comment '��ʵ����',
   password             varchar(80) not null comment '����',
   password_salt        varchar(20) not null comment '������',
   activated            bit not null comment '�Ѽ���',
   enabled              bit not null comment '������',
   deleted              bit not null comment '��ɾ��',
   create_time          datetime not null comment '����ʱ��',
   creator              varchar(80) not null comment '������',
   modify_time          datetime not null comment '�޸�ʱ��',
   modifier             varchar(80) not null comment '�޸���',
   access_time          datetime comment '����ʱ��',
   access_ip            varchar(40) comment '����ip��ַ',
   primary key (id)
);

alter table user comment '�û�';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   bigint not null auto_increment comment '�û���ɫ��ϵ��ʶ��',
   user_id              bigint not null comment '�û���ʶ��',
   role_id              bigint not null comment '��ɫ��ʶ��',
   primary key (id)
);

alter table user_role comment '�û���ɫ��ϵ';

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

