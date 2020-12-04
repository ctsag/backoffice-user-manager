alter table tadminop
    add o_type char(8) default 'GEN' not null,
    add disporder smallint;

rename column
    tadminop.o_type to type;
