alter table tadminuser
    modify password varchar(40) not null,
    modify lname varchar(60),
    add constraint check(password != '') constraint cadminuser_c2;
