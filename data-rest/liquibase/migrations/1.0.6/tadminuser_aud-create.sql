create table tadminuser_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    user_id integer,
    username varchar(32),
    password varchar(40),
    fname varchar(60),
    lname varchar(60),
    login_uid integer,
    status char(1),
    email varchar(60),
    agent_id varchar(32),
    phone_switch integer,
    logged_in char(1),
    position_id integer,
    login_time datetime year to second,
    login_loc varchar(32),
    last_pwd_change datetime year to second,
    override_code varchar(2),
    bad_pwd_count integer,
    last_pwd_fail datetime year to second,
    password_salt varchar(40),
    acc_pwd_expires char(1),
    lost_login_status char(1),
    timezone_id integer
);
