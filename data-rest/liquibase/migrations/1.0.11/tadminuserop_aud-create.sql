create table tadminuserop_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    user_id integer,
    action varchar(64)
);
