create table tadmingroupop (
    group_id integer not null,
    action varchar(64) not null,
    primary key (group_id, action) constraint cadmingroupop_pk
);
