create trigger
    rdadmingroupop
delete on
    tadmingroupop
referencing
    old as pre
for each row (
    execute procedure paudadmingroupop(
        p_aud_op = 'D',
        p_group_id_post = pre.group_id,
        p_action_post = pre.action
    )
);
