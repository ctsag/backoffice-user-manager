create table tadmingroupop_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    group_id integer,
    action varchar(64)
);
