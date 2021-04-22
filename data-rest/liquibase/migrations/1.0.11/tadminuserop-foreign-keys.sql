alter table tadminuserop
    add constraint (foreign key (user_id) references tadminuser constraint cadminuserop_f1),
    add constraint (foreign key (action) references tadminop constraint cadminuserop_f2);
