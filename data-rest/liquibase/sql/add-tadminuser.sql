--liquibase formatted sql
create table tadminuser (
    user_id serial not null,
    username varchar(32) not null,
    password varchar(40) not null,
    fname varchar(60),
    lname varchar(80),
    primary key (user_id) constraint cadminuser_pk
);
