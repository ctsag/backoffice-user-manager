delete from
  tadmingroupop
where
  group_id in (
    select group_id from tadmingroup where group_name = "Privileged users"
  )
and
  action in ("UseAdmin", "AssignRights");
