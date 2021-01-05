create trigger
    rijurisdiction
insert on
    tjurisdiction
referencing
    new as post
for each row (
    execute procedure paudjurisdiction(
        p_aud_op = 'I',
        p_jur_id_post = post.jur_id,
        p_jurisdiction_post = post.jurisdiction
    )
);
