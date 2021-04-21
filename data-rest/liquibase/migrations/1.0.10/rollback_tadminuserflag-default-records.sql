delete from
    tadminuserflag
where
    user_id in (select user_id from tadminuser where username = "Administrator")
and
    flag_name = "IS_AUTOMATED";
