create procedure paudadminusergroup (
    p_aud_op like tadminusergroup_aud.aud_op,
    p_user_id_pre like tadminusergroup_aud.user_id default null,
    p_user_id_post like tadminusergroup_aud.user_id,
    p_group_id_pre like tadminusergroup_aud.group_id default null,
    p_group_id_post like tadminusergroup_aud.group_id
)
    define v_admin_id int;

    let v_admin_id = pSetGAdminUser();

    insert into tadminusergroup_aud (
        aud_id,
        aud_time,
        aud_op,
        user_id,
        group_id
    ) values (
        v_admin_id,
        CURRENT,
        p_aud_op,
        p_user_id_post,
        p_group_id_post
    );

end procedure;

--END
