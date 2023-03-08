drop table if exists user_core;
create table user_core
(
    id          int          not null auto_increment,
    username    varchar(255) not null,
    create_time datetime     not null default now(),
    modify_time datetime     not null default now(),
    primary key (id),
    unique key UNIQUE_USERNAME (username)
);

insert into user_core(username)
values ('aaa'),
       ('bbb');

drop table if exists user_wallet;
create table user_wallet
(
    id          int      not null auto_increment,
    user_id     int      not null,
    money       bigint   not null default 0,
    create_time datetime not null default now(),
    modify_time datetime not null default now(),
    primary key (id),
    index USER_ID_MONEY (user_id, money)
);

insert into user_wallet(user_id, money)
values ((select id from user_core where username = 'aaa'), 120),
       ((select id from user_core where username = 'bbb'), 0);

drop table if exists user_order;
create table user_order
(
    id          int          not null auto_increment,
    user_id     int          not null,
    money       bigint       not null,
    details     varchar(255) not null default '',
    is_success  boolean      not null default false,
    is_refund   boolean      not null default false,
    create_time datetime     not null default now(),
    modify_time datetime     not null default now(),
    primary key (id),
    index USER_ID (user_id)
);

insert into user_order(user_id, money, details)
values ((select id from user_core where username = 'aaa'), -20, '消费20订单')