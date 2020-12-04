create procedure psetgadminuser (
    p_admin_id int default null,
    p_username like tAdminUser.username default null
) returning int;
	define global g_admin_id int default -1;

    if p_admin_id is not null then
        let g_admin_id = p_admin_id;
    elif p_username is not null then
		select
			user_id
		into
			p_admin_id
		from
			tAdminUser
		where
			username = p_username;

		if DBINFO('sqlca.sqlerrd2') = 1 then
			let g_admin_id = p_admin_id;
		else
			raise exception -746,0,"Cant find user "||p_username;
		end if;
	end if

	return g_admin_id;

end procedure;

--END
