alter table tadminposition
    add constraint (foreign key (parent_position_id) references tadminposition constraint cadminposition_f1);
