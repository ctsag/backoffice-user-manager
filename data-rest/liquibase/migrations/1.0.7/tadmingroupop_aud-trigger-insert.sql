create trigger
    riadmingroupop
insert on
    tadmingroupop
referencing
    new as post
for each row (
    execute procedure paudadmingroupop(
        p_aud_op = 'I',
        p_group_id_post = post.group_id,
        p_action_post = post.action
    )
);
