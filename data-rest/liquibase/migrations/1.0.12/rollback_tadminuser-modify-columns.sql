alter table tadminuser
    modify password varchar(255),
    modify lname varchar(80),
    drop constraint cadminuser_c2;
