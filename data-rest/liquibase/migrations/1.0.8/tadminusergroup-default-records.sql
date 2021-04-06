insert into tadminusergroup (
  user_id,
  group_id
) values (
  (select user_id from tadminuser where username = "PrivilegedUser"),
  (select group_id from tadmingroup where group_name = "Privileged users")
);
