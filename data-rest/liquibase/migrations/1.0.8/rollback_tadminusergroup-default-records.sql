delete from
    tadminusergroup
where
    user_id in (
      select user_id from tadminuser where username = "PrivilegedUser"
    )
and
    group_id in (
      select group_id from tadmingroup where group_name = "Privileged users"
    );
