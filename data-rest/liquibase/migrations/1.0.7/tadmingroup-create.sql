create table tadmingroup (
    group_id serial not null,
    group_name varchar(64) not null,
    group_owner integer,
    cr_date datetime year to second default current year to second not null,
    unique (group_name) constraint cadmingroup_u1,
    check (group_name != "") constraint cadmingroup_c1,
    primary key (group_id) constraint cadmingroup_pk
);
