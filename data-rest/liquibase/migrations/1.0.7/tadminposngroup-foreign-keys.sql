alter table tadminposngroup
    add constraint (foreign key (position_id) references tadminposition constraint tadminposngroup_f1),
    add constraint (foreign key (group_id) references tadmingroup constraint tadminposngroup_f2);
