create procedure paudadminuserop (
    p_aud_op like tadminuserop_aud.aud_op,
    p_user_id_pre like tadminuserop_aud.user_id default null,
    p_user_id_post like tadminuserop_aud.user_id,
    p_action_pre like tadminuserop_aud.action default null,
    p_action_post like tadminuserop_aud.action
)
    define v_admin_id int;

    let v_admin_id = pSetGAdminUser();

    insert into tadminuserop_aud (
        aud_id,
        aud_time,
        aud_op,
        user_id,
        action
    ) values (
        v_admin_id,
        CURRENT,
        p_aud_op,
        p_user_id_post,
        p_action_post
    );

end procedure;

--END
