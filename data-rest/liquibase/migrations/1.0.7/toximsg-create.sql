create table toximsg (
    msg_id serial8 not null,
    cr_date datetime year to second default current year to second not null,
    o_type char(1) not null,
    level varchar(32) not null,
    aud_order int8 not null,
    aud_id integer,
    base_id varchar(32) default '' not null,
    priority integer default 0 not null,
    delta_columns varchar(255),
    delta_values varchar(255),
    delta_unchanged varchar(255),
    ps_msg_id int8,
    partition_id int8 default 1,
    check (o_type in ('I', 'U', 'D')) constraint coximsg_c1
);

rename column toximsg.o_type to type;
