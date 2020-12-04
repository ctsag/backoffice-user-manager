alter table tadminuser
    add constraint (foreign key (position_id) references tadminposition constraint tadminuser_f1),
    add constraint (foreign key (timezone_id) references ttimezone constraint tadminuser_f2);
