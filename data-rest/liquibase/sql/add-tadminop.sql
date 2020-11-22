create table tadminop (
    action varchar(64) not null,
    desc varchar(80) not null,
    check ((action != '') and (desc != '')) constraint cadminop_c1,
    primary key (action) constraint cadminop_pk
);
