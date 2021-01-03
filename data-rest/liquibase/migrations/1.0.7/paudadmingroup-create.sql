create procedure paudadmingroup (
    p_aud_op like tadmingroup_aud.aud_op,
    p_group_id_pre like tadmingroup_aud.group_id default null,
    p_group_id_post like tadmingroup_aud.group_id,
    p_group_name_pre like tadmingroup_aud.group_name default null,
    p_group_name_post like tadmingroup_aud.group_name,
    p_group_owner_pre like tadmingroup_aud.group_owner default null,
    p_group_owner_post like tadmingroup_aud.group_owner,
    p_cr_date_pre like tadmingroup_aud.cr_date default null,
    p_cr_date_post like tadmingroup_aud.cr_date
)
    define v_admin_id int;

    let v_admin_id = pSetGAdminUser();

    insert into tadmingroup_aud (
        aud_id,
        aud_time,
        aud_op,
        group_id,
        group_name,
        group_owner,
        cr_date
    ) values (
        v_admin_id,
        CURRENT,
        p_aud_op,
        p_group_id_post,
        p_group_name_post,
        p_group_owner_post,
        p_cr_date_post
    );

end procedure;

--END
