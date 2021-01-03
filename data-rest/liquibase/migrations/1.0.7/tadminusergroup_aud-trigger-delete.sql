create trigger
    rdadminusergroup
delete on
    tadminusergroup
referencing
    old as pre
for each row (
    execute procedure paudadminusergroup(
        p_aud_op = 'D',
        p_user_id_post = pre.user_id,
        p_group_id_post = pre.group_id
    )
);
