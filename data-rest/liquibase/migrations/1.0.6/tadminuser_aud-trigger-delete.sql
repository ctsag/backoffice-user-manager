create trigger
    rdadminuser
delete on
    tadminuser 
referencing
    old as pre
for each row (
    execute procedure paudadminuser(
        p_aud_op = 'D',
        p_user_id_post = pre.user_id,
        p_username_post = pre.username,
        p_password_post = pre.password,
        p_fname_post = pre.fname,
        p_lname_post = pre.lname,
        p_login_uid_post = pre.login_uid,
        p_status_post = pre.status,
        p_email_post = pre.email,
        p_agent_id_post = pre.agent_id,
        p_phone_switch_post = pre.phone_switch,
        p_logged_in_post = pre.logged_in,
        p_position_id_post = pre.position_id,
        p_login_time_post = pre.login_time,
        p_login_loc_post = pre.login_loc,
        p_last_pwd_change_post = pre.last_pwd_change,
        p_override_code_post = pre.override_code,
        p_bad_pwd_count_post = pre.bad_pwd_count,
        p_last_pwd_fail_post = pre.last_pwd_fail,
        p_password_salt_post = pre.password_salt,
        p_acc_pwd_expires_post = pre.acc_pwd_expires,
        p_lost_login_status_post = pre.lost_login_status,
        p_timezone_id_post = pre.timezone_id
    )
);
