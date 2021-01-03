create trigger
    rdadmingroup
delete on
    tadmingroup
referencing
    old as pre
for each row (
    execute procedure paudadmingroup(
        p_aud_op = 'D',
        p_group_id_post = pre.group_id,
        p_group_name_post = pre.group_name,
        p_group_owner_post = pre.group_owner,
        p_cr_date_post = pre.cr_date
    )
);
