package gr.nothingness.backofficeusermanager.entities;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.nothingness.backofficeusermanager.validation.ValidPassword;
import gr.nothingness.backofficeusermanager.security.facilities.MixedDigestPasswordEncoder;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tadminuser")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ValidPassword
public class BackofficeUser {

  @RequiredArgsConstructor
  protected enum Status {

    A ("Active"),
    S ("Suspended"),
    P ("Password expired"),
    L ("Locked"),
    X ("Deleted");

    @Getter private final String description;

  }

  @RequiredArgsConstructor
  private enum LostLoginStatus {

    A ("Active"),
    S ("Suspended"),
    X ("Deleted");

    @Getter private final String description;

  }

  @RequiredArgsConstructor
  private enum YesNo {

    Y ("Yes"),
    N ("No");

    @Getter private final String description;

  }

  @Column(name = "user_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  @Getter private Long id;

  @Column(name = "username", unique = true)
  @NaturalId
  @NotNull @Size(min = 1, max = 32)
  @Getter @Setter private String username;

  @Column(name = "password")
  @JsonProperty(access = WRITE_ONLY)
  @Getter @Setter private String password;

  @Transient
  private String currentPassword;

  @Transient
  @JsonProperty(access = WRITE_ONLY)
  @Getter private String plainTextPassword;

  @Column(name = "fname")
  @Size(max = 60)
  @Getter @Setter private String firstName;

  @Column(name = "lname")
  @Size(max = 60)
  @Getter @Setter private String lastName;

  @Column(name = "email")
  @Size(max = 60) @Email
  @Getter @Setter private String email;

  @Column(name = "status")
  @NotNull @Enumerated(STRING)
  @Getter @Setter private Status status = Status.A;

  @Column(name = "acc_pwd_expires")
  @NotNull @Enumerated(STRING)
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
  @JsonIgnore @Temporal(TIMESTAMP)
  @Getter @Setter private Date loginTime;

  @Column(name = "login_loc")
  @JsonIgnore @Size(max = 32)
  @Getter @Setter private String loginLocation;

  @Column(name = "last_pwd_change")
  @JsonIgnore @Temporal(TIMESTAMP)
  @Getter @Setter private Date lastPasswordChange = new Date();

  @Column(name = "bad_pwd_count")
  @JsonIgnore @NotNull @Min(0)
  @Getter @Setter private Integer badPasswordCount = 0;

  @Column(name = "last_pwd_fail")
  @JsonIgnore @Temporal(TIMESTAMP)
  @Getter @Setter private Date lastPasswordFail;

  @Column(name = "password_salt")
  @JsonProperty(access = WRITE_ONLY)
  @Size(max = 40)
  @Getter private String passwordSalt;

  @Column(name = "lost_login_status")
  @JsonIgnore @NotNull @Enumerated(STRING)
  @Getter @Setter private LostLoginStatus lostLoginStatus = LostLoginStatus.A;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "timezone_id")
  @Getter @Setter private Timezone timezone;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "position_id")
  @Getter @Setter private Position position;

  @OneToMany(mappedBy = "owner")
  @JsonIgnore
  @Getter @Setter private Set<BackofficeGroup> ownedGroups;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "tadminuserop",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private Set<Permission> permissions;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "tadminusergroup",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  @Getter @Setter private Set<BackofficeGroup> groups;

  @OneToMany(mappedBy = "user", cascade = ALL)
  @Getter @Setter private Set<FlagValue> flags;

  @PostLoad
  private void storeCurrentPassword() {
    currentPassword = password;
  }

  @PrePersist @PreUpdate
  private void encodePassword() {
    if (password != null && !password.isEmpty() && !password.equals(currentPassword)) {
      MixedDigestPasswordEncoder passwordEncoder = new MixedDigestPasswordEncoder();

      plainTextPassword = password;
      password = passwordEncoder.encode(password);
      passwordSalt = passwordEncoder.getSalt();
    }
  }

  @PreRemove
  private void disassociate() {
    ownedGroups.forEach(group -> group.setOwner(null));
  }

  public void removeGroup(BackofficeGroup group) {
    groups.remove(group);
  }

  public void removePermission(Permission permission) {
    permissions.remove(permission);
  }

}
