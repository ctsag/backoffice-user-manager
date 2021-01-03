create table tadminusergroup (
    user_id integer not null,
    group_id integer not null,
    primary key (user_id, group_id) constraint cadminusergroup_pk
);
