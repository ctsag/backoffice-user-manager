alter table tadminuserflag
    add constraint (foreign key (user_id) references tadminuser constraint cadminuserflag_f1),
    add constraint (foreign key (flag_name) references tadminuserflagdesc constraint cadminuserflag_f2);
