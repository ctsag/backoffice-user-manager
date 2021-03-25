create trigger
    ruadmingroupop
update on
    tadmingroupop
referencing
    old as pre
    new as post
for each row (
    execute procedure paudadmingroupop(
        p_aud_op = 'U',
        p_group_id_post = post.group_id,
        p_action_post = post.action
    )
);
