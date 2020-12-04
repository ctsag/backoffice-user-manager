create table tadminuserop (
    user_id integer not null,
    action varchar(64) not null,
    primary key (user_id, action) constraint cadminuserop_pk
);
