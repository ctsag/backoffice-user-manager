delete from
    tadminuserop
where
    user_id in (select user_id from tadminuser where username = "Administrator")
and
    action in ("ReadAccess", "WriteAccess");
