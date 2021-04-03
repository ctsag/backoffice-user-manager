insert into tadminusergroup (
  user_id,
  group_id
) values (
  (select user_id from tadminuser where username = "Administrator"),
  (select group_id from tadmingroup where group_name = "Assign permissions")
);
