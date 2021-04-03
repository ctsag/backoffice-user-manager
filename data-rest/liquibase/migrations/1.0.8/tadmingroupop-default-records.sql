insert into tadmingroupop (group_id, action) values ((select group_id from tadmingroup where group_name = "Assign permissions"), "UseAdmin");
insert into tadmingroupop (group_id, action) values ((select group_id from tadmingroup where group_name = "Assign permissions"), "AssignRights");
