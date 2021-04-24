create trigger
    rdadminuserop
delete on
    tadminuserop
referencing
    old as pre
for each row (
    execute procedure paudadminuserop(
        p_aud_op = 'D',
        p_user_id_post = pre.user_id,
        p_action_post = pre.action
    )
);
