create table tadmingroup_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    group_id integer,
    group_name varchar(64),
    group_owner integer,
    cr_date datetime year to second
);
