create trigger
    ruadminuser
update on
    tadminuser 
referencing
    old as pre
    new as post
for each row (
    execute procedure paudadminuser(
        p_aud_op = 'U',
        p_user_id_post = post.user_id,
        p_username_post = post.username,
        p_password_post = post.password,
        p_fname_post = post.fname,
        p_lname_post = post.lname,
        p_login_uid_post = post.login_uid,
        p_status_post = post.status,
        p_email_post = post.email,
        p_agent_id_post = post.agent_id,
        p_phone_switch_post = post.phone_switch,
        p_logged_in_post = post.logged_in,
        p_position_id_post = post.position_id,
        p_login_time_post = post.login_time,
        p_login_loc_post = post.login_loc,
        p_last_pwd_change_post = post.last_pwd_change,
        p_override_code_post = post.override_code,
        p_bad_pwd_count_post = post.bad_pwd_count,
        p_last_pwd_fail_post = post.last_pwd_fail,
        p_password_salt_post = post.password_salt,
        p_acc_pwd_expires_post = post.acc_pwd_expires,
        p_lost_login_status_post = post.lost_login_status,
        p_timezone_id_post = post.timezone_id
    )
);
