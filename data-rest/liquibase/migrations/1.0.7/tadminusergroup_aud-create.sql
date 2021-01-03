create table tadminusergroup_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    user_id integer,
    group_id integer
);
