alter table tadminusergroup
    add constraint (foreign key (user_id) references tadminuser constraint tadminusergroup_f1),
    add constraint (foreign key (group_id) references tadmingroup constraint tadminusergroup_f2);
