alter table tadminuser
    add constraint unique(username) constraint cadminuser_u1,
    add constraint check(username != '') constraint cadminuser_c1,
    add constraint check(password != '') constraint cadminuser_c2,
    add constraint check(status in ('A', 'S', 'P', 'L', 'X')) constraint cadminuser_c3,
    add constraint check(bad_pwd_count >= 0) constraint cadminuser_c4,
    add constraint check(acc_pwd_expires in ('Y', 'N')) constraint cadminuser_c5,
    add constraint check(lost_login_status in ('A', 'S', 'L')) constraint cadminuser_c6;
