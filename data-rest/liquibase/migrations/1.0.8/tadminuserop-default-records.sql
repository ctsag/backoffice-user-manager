insert into tadminuserop values ((select user_id from tadminuser where username = "Administrator"), "ReadAccess");
insert into tadminuserop values ((select user_id from tadminuser where username = "Administrator"), "WriteAccess");
