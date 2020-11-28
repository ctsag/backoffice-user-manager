package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tadminuser")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeUser {

  @Column(name = "user_id")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter private Long id;

  @Column(name = "username")
  @NotNull @Size(max = 32)
  @Getter @Setter private String username;

  @Column(name = "password")
  @NotNull @Size(max = 40)
  @Getter @Setter private String password;

  @Column(name = "fname")
  @Size(max = 60)
  @Getter @Setter private String firstName;

  @Column(name = "lname")
  @Size(max = 80)
  @Getter @Setter private String lastName;

  @Column(name = "login_uid")
  @NotNull
  @Getter @Setter private Integer loginUid = 0;

  @Column(name = "status")
  @NotNull
  @Getter @Setter private Character status = 'A';

  @Column(name = "email")
  @Size(max = 60)
  @Getter @Setter private String email;

  @Column(name = "agent_id")
  @Size(max = 32)
  @Getter @Setter private String agent;

  @Column(name = "phone_switch")
  @Getter @Setter private Integer phoneSwitch;

  @Column(name = "logged_in")
  @NotNull
  @Getter @Setter private Character loggedIn = 'N';

  @Column(name = "position_id")
  @Getter @Setter private Integer positionId;

  @Column(name = "login_time")
  @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date loginTime;

  @Column(name = "login_loc")
  @Size(max = 32)
  @Getter @Setter private String loginLocation;

  @Column(name = "last_pwd_change")
  @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date lastPasswordChange = new Date();

  @Column(name = "override_code")
  @Size(max = 2)
  @Getter @Setter private String overrideCode;

  @Column(name = "bad_pwd_count")
  @NotNull
  @Getter @Setter private Integer badPasswordCount = 0;

  @Column(name = "last_pwd_fail")
  @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date lastPasswordFail;

  @Column(name = "password_salt")
  @Size(max = 40)
  @Getter @Setter private String passwordSalt;

  @Column(name = "acc_pwd_expires")
  @NotNull
  @Getter @Setter private Character passwordExpires = 'Y';

  @Column(name = "lost_login_status")
  @NotNull
  @Getter @Setter private Character lostLoginStatus = 'A';

  @Column(name = "timezone_id")
  @Getter @Setter private Integer timezoneId;

  @ManyToMany
  @JoinTable(
      name = "tadminuserop",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private List<Permission> permissions;

}
