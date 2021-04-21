create table tadminuserflagdesc (
    flag_name varchar(32) not null,
    description varchar(255),
    note varchar(255),
    default_val varchar(64),
    primary key (flag_name) constraint cadmusrflagdesc_pk
);
