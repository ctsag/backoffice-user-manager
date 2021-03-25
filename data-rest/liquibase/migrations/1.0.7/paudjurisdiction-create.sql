create procedure paudjurisdiction (
    p_aud_op like tjurisdiction_aud.aud_op,
    p_jur_id_pre like tjurisdiction_aud.jur_id default null,
    p_jur_id_post like tjurisdiction_aud.jur_id,
    p_jurisdiction_pre like tjurisdiction_aud.jurisdiction default null,
    p_jurisdiction_post like tjurisdiction_aud.jurisdiction
)
    define v_admin_id int;
    define v_columns_len int;
  	define v_values_len int;
	define v_delta_columns varchar(255);
	define v_delta_values varchar(255);
	define v_delta_too_long char(1);
	define v_do_replicate int;
	define err_code int;
	define err_isam int;
	define err_msg varchar(255);
	define v_aud_order int;

	-- if this exception fires, a concatenated varchar was too long
	on exception set err_code, err_isam, err_msg
	if err_code = -881 then
	    let v_delta_too_long = 'Y';
	else
		raise exception err_code,err_isam,err_msg;
	end if
	end exception with resume

    let v_admin_id = pSetGAdminUser();

    let v_delta_columns = '';
	let v_delta_values = '';
	let v_delta_too_long = 'N';
	let v_do_replicate = 1;

	if p_aud_op == 'U' then
		let v_do_replicate = 0;
		if p_jur_id_pre <> p_jur_id_post then
			let v_delta_columns = v_delta_columns || 'jur_id' || ',';
			let v_delta_values  = v_delta_values || p_jur_id_post || ',';
			let v_do_replicate = 1;
		end if;
		if p_jurisdiction_pre <> p_jurisdiction_post then
			let v_delta_columns = v_delta_columns || 'jurisdiction' || ',';
			let v_delta_values  = v_delta_values || REPLACE(REPLACE(p_jurisdiction_post, '\','\\'),',','\,') || ',';
			let v_do_replicate = 1;
		end if;
	end if;

	let v_columns_len = octet_length(v_delta_columns);
	let v_values_len = octet_length(v_delta_values);
	if v_delta_too_long == 'Y' or v_values_len > 254 or v_columns_len > 254 then
		let v_delta_columns = null;
		let v_delta_values = null;
	else
		-- strip the trailing comma
		if v_delta_columns == '' then
			let v_delta_columns = null;
		else
			let v_delta_columns = substring(v_delta_columns from 1 for v_columns_len - 1);
		end if;
		if v_delta_values == '' then
			let v_delta_values = null;
		else
			let v_delta_values = substring(v_delta_values from 1 for v_values_len - 1);
		end if;
	end if;

    insert into tjurisdiction_aud (
        aud_id,
        aud_time,
        aud_op,
        jur_id,
        jurisdiction
    ) values (
        v_admin_id,
        CURRENT,
        p_aud_op,
        p_jur_id_post,
        p_jurisdiction_post
    );

	let v_aud_order = DBINFO('sqlca.sqlerrd1');
	-- write to tOXiMsg if one or more of the columns that
	-- trigger replication have changed

	if v_do_replicate == 1 then
		insert into toximsg (
			type,
			level,
			aud_order,
			cr_date,
			aud_id,
			base_id,
			delta_columns,
			delta_values
		) values (
			p_aud_op,
			'JURISDICTION',
			v_aud_order,
			CURRENT,
			v_admin_id,
			p_jur_id_post,
			v_delta_columns,
			v_delta_values
		);
	end if;

end procedure;

--END
