insert into
  tadminuserop
values (
  (select user_id from tadminuser where username = "Administrator"),
  "UseAdmin"
);

insert into
  tadminuserop
values (
  (select user_id from tadminuser where username = "Administrator"),
  "AssignRights"
);
