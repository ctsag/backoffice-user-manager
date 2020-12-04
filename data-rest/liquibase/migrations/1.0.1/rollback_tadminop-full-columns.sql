rename column
    tadminop.type to o_type;

alter table tadminop
    drop o_type,
    drop disporder;
