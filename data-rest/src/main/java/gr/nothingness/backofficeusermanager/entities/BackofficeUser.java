package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import gr.nothingness.backofficeusermanager.facilities.Password;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(
    name = "tadminuser",
    indexes = @Index(name = "iadminuser_x1", columnList = "timezone_id")
)
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeUser {

  private enum Status {
    A, S, P, L, X
  }

  private enum LostLoginStatus {
    A, S, X
  }

  private enum YesNo {
    Y, N
  }

  @Column(name = "user_id")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter private Long id;

  @Column(name = "username", unique = true)
  @NaturalId
  @NotNull @Size(min = 1, max = 32)
  @Getter @Setter private String username;

  @Column(name = "password")
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull @Size(min = 1, max = 40)
  @Getter private String password;

  @Column(name = "fname")
  @Size(max = 60)
  @Getter @Setter private String firstName;

  @Column(name = "lname")
  @Size(max = 80)
  @Getter @Setter private String lastName;

  @Column(name = "email")
  @Size(max = 60) @Email
  @Getter @Setter private String email;

  @Column(name = "status")
  @NotNull @Enumerated(EnumType.STRING)
  @Getter @Setter private Status status = Status.A;

  @Column(name = "acc_pwd_expires")
  @NotNull @Enumerated(EnumType.STRING)
  @Getter @Setter private YesNo passwordExpires = YesNo.Y;

  @Column(name = "agent_id")
  @Size(max = 32)
  @Getter @Setter private String agent;

  @Column(name = "phone_switch")
  @Getter @Setter private Integer phoneSwitch;

  @Column(name = "override_code")
  @Size(max = 2)
  @Getter @Setter private String overrideCode;

  @Column(name = "login_uid")
  @JsonIgnore @NotNull
  @Getter @Setter private Integer loginUid = 0;

  @Column(name = "logged_in")
  @JsonIgnore @NotNull
  @Getter @Setter private Character loggedIn = 'N';

  @Column(name = "login_time")
  @JsonIgnore @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date loginTime;

  @Column(name = "login_loc")
  @JsonIgnore @Size(max = 32)
  @Getter @Setter private String loginLocation;

  @Column(name = "last_pwd_change")
  @JsonIgnore @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date lastPasswordChange = new Date();

  @Column(name = "bad_pwd_count")
  @JsonIgnore @NotNull @Min(0)
  @Getter @Setter private Integer badPasswordCount = 0;

  @Column(name = "last_pwd_fail")
  @JsonIgnore @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date lastPasswordFail;

  @Column(name = "password_salt")
  @JsonIgnore @Size(max = 40)
  @Getter private String passwordSalt;

  @Column(name = "lost_login_status")
  @JsonIgnore @NotNull @Enumerated(EnumType.STRING)
  @Getter @Setter private LostLoginStatus lostLoginStatus = LostLoginStatus.A;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "timezone_id")
  @Getter @Setter private Timezone timezone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "position_id")
  @Getter @Setter private Position position;

  @OneToMany(mappedBy = "owner")
  @JsonIgnore
  @Getter @Setter private List<BackofficeGroup> ownedGroups;

  @ManyToMany
  @JoinTable(
      name = "tadminuserop",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private List<Permission> permissions;

  @ManyToMany
  @JoinTable(
      name = "tadminusergroup",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  @Getter @Setter private List<BackofficeGroup> groups;

  public void setPassword(String password) throws NoSuchAlgorithmException {
    passwordSalt = Password.generateSalt();
    this.password = Password.encryptPassword(password, passwordSalt);
  }

}
