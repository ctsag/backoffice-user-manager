create trigger
    rdjurisdiction
delete on
    tjurisdiction
referencing
    old as pre
for each row (
    execute procedure paudjurisdiction(
        p_aud_op = 'D',
        p_jur_id_post = pre.jur_id,
        p_jurisdiction_post = pre.jurisdiction
    )
);
