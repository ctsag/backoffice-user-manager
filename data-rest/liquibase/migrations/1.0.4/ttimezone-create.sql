create table ttimezone (
    timezone_id serial not null,
    name varchar(80) not null,
    status char(1) default 'A',
    display char(1) default 'N',
    check (status IN ('A', 'S', 'D')) constraint ctimezone_c1,
    check (display IN ('Y', 'N')) constraint ctimezone_c2
);
