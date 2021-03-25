alter table tadmingroupop
    add constraint (foreign key (group_id) references tadmingroup constraint tadmingroupop_f1),
    add constraint (foreign key (action) references tadminop constraint tadmingroupop_f2);
