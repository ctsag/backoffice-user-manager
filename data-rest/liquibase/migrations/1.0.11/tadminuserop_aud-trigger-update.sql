create trigger
    ruadminuserop
update on
    tadminuserop
referencing
    old as pre
    new as post
for each row (
    execute procedure paudadminuserop(
        p_aud_op = 'U',
        p_user_id_post = post.user_id,
        p_action_post = post.action
    )
);
