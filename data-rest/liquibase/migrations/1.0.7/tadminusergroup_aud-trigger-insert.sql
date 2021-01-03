create trigger
    riadminusergroup
insert on
    tadminusergroup
referencing
    new as post
for each row (
    execute procedure paudadminusergroup(
        p_aud_op = 'I',
        p_user_id_post = post.user_id,
        p_group_id_post = post.group_id
    )
);
