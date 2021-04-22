create trigger
    riadminuserop
insert on
    tadminuserop
referencing
    new as post
for each row (
    execute procedure paudadminuserop(
        p_aud_op = 'I',
        p_user_id_post = post.user_id,
        p_action_post = post.action
    )
);
