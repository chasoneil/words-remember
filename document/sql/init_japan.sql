-- init table japan

create table tbl_jp_words (
    id int primary key auto_increment,
    class_id int comment '第几课',
    group_id int comment '分组',
    is_rem int not null comment '是否记住',
    w_write varchar(255) not null comment '写法',
    w_read varchar(255) not null comment '读法',
    w_mean varchar(255) not null comment '中文含义',
    create_time datetime not null DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    first_learn_time date comment '首次学习时间',
    last_learn_time date comment '上次学习时间',
    update_time datetime not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后变更时间'
);


create table class_info (
    id int not null primary key comment 'class id',
    name varchar(255) not null comment '课程名称'
);

create table group_info (
    id int not null primary key comment 'group id',
    name varchar(255) not null comment '分组名称'
);


