create table tadminuserflag (
    user_id integer,
    flag_name varchar(32) not null ,
    flag_value varchar(64),
    primary key (user_id, flag_name) constraint cadminuserflag_pk
);
