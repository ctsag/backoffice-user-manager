insert into tadmingroup (
  group_name,
  group_owner
) values (
  "Privileged users",
  (select user_id from tadminuser where username = "Administrator")
);
