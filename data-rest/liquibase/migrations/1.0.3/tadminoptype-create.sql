create table tadminoptype (
    type char(8) not null,
    desc varchar(40) not null,
    disporder smallint,
    unique (desc) constraint cadmninoptype_u1,
    check ((type != '') AND (desc != '')) constraint cadminoptype_c1,
    primary key (type) constraint cadminoptype_pk
);
