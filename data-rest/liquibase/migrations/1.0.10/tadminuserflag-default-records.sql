insert into
    tadminuserflag
values (
    (select user_id from tadminuser where username = "Administrator"),
    "IS_AUTOMATED",
    1
);
