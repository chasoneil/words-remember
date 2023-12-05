create table user(
    id int auto_increment primary key comment '主键',
    no varchar(255) comment '账号',
    name varchar(100) not null comment '用户名',
    password varchar(1024) not null comment '密码',
    age int comment '年龄',
    sex int comment '性别',
    phone varchar(20) comment '电话号码',
    role_id int not null comment '角色 0超级管理员 1管理员 2普通账号',
    is_valid int not null default 1 comment '账号状态 0 冻结 1 正常状态 2 已删除',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '最后变更时间'
) charset = utf8;

insert into user (id, no, name, password, age, sex, phone, role_id) values (1, 'admin', 'admin', 'admin123', 20, 1, '10001', 0, 1);
