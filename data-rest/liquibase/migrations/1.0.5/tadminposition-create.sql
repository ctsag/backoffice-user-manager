create table tadminposition (
    position_id serial not null ,
    parent_position_id integer,
    position_name varchar(64) not null ,
    primary key (position_id) constraint cadminposition_pk
);
