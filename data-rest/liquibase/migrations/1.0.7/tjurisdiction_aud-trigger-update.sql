create trigger
    rujurisdiction
update on
    tjurisdiction
referencing
    old as pre
    new as post
for each row (
    execute procedure paudjurisdiction(
        p_aud_op = 'U',
        p_jur_id_post = post.jur_id,
        p_jurisdiction_post = post.jurisdiction
    )
);
