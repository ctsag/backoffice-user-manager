insert into tadmingroup (
  group_name,
  group_owner
) values (
  "Assign permissions",
  (select user_id from tadminuser where username = "Administrator")
);
