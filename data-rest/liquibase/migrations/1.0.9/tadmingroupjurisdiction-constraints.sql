alter table tadmingroupjurisdiction
    add constraint (foreign key (group_id) references tadmingroup constraint cadmingroupjurisdiction_f1),
    add constraint (foreign key (jur_id) references tjurisdiction constraint cadmingroupjurisdiction_f2);
