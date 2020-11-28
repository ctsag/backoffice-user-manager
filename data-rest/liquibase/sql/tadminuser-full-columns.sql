--liquibase formatted sql
alter table tadminuser
    add login_uid integer default 0 not null,
    add status char(1) default 'A' not null,
    add email varchar(60),
    add agent_id varchar(32) default null,
    add phone_switch integer default null,
    add logged_in char(1) default 'N' not null,
    add position_id integer,
    add login_time datetime year to second,
    add login_loc varchar(32),
    add last_pwd_change datetime year to second default current year to second not null,
    add override_code varchar(2),
    add bad_pwd_count integer default 0 not null,
    add last_pwd_fail datetime year to second,
    add password_salt varchar(40),
    add acc_pwd_expires char(1) default 'Y' not null,
    add lost_login_status char(1) default 'A' not null,
    add timezone_id integer;
