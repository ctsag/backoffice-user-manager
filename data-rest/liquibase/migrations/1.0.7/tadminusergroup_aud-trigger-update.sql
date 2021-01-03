create trigger
    ruadminusergroup
update on
    tadminusergroup
referencing
    old as pre
    new as post
for each row (
    execute procedure paudadminusergroup(
        p_aud_op = 'U',
        p_user_id_post = post.user_id,
        p_group_id_post = post.group_id
    )
);
