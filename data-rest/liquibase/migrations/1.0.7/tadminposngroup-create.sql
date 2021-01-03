create table tadminposngroup (
    position_id integer not null,
    group_id integer not null,
    primary key (position_id, group_id) constraint cadminposngroup_pk
);
