create procedure paudadmingroupop (
    p_aud_op like tadminusergroup_aud.aud_op,
    p_group_id_pre like tadmingroupop_aud.group_id default null,
    p_group_id_post like tadmingroupop_aud.group_id,
    p_action_pre like tadmingroupop_aud.action default null,
    p_action_post like tadmingroupop_aud.action
)
    define v_admin_id int;

    let v_admin_id = pSetGAdminUser();

    insert into tadmingroupop_aud (
        aud_id,
        aud_time,
        aud_op,
        group_id,
        action
    ) values (
        v_admin_id,
        CURRENT,
        p_aud_op,
        p_group_id_post,
        p_action_post
    );

end procedure;

--END
