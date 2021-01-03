create trigger
    ruadmingroup
update on
    tadmingroup
referencing
    old as pre
    new as post
for each row (
    execute procedure paudadmingroup(
        p_aud_op = 'U',
        p_group_id_post = post.group_id,
        p_group_name_post = post.group_name,
        p_group_owner_post = post.group_owner,
        p_cr_date_post = post.cr_date
    )
);
