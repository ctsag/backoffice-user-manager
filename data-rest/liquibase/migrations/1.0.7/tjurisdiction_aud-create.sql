create table tjurisdiction_aud (
    aud_order serial not null,
    aud_id integer,
    aud_time datetime year to second,
    aud_op char(1),
    jur_id integer,
    jurisdiction varchar(64)
);
