package gr.nothingness.backofficeusermanager.security.entities;


import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tadminuser")
@NoArgsConstructor @Getter @Setter
public class AuthUser implements UserDetails {

  @RequiredArgsConstructor @Getter
  private enum Status {

    A ("Active"),
    S ("Suspended"),
    P ("Password expired"),
    L ("Locked"),
    X ("Deleted");

    private final String description;

  }

  @Column(name = "user_id")
  @Id
  private Long id;

  @Column(name = "username", unique = true)
  @NaturalId
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "status")
  @Enumerated(STRING)
  private Status status = Status.A;

  @Column(name = "password_salt")
  @Setter(AccessLevel.NONE)
  private String passwordSalt;

  @ManyToMany(fetch = EAGER)
  @JoinTable(
      name = "tadminuserop",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  private Set<AuthPermission> permissions;

  @ManyToMany(fetch = EAGER)
  @JoinTable(
      name = "tadminusergroup",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  private Set<AuthGroup> groups;

  public AuthUser(AuthUser user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.passwordSalt = user.getPasswordSalt();
    this.status = user.getStatus();
    this.groups = user.getGroups();
    this.permissions = user.getPermissions();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return status != Status.P;
  }

  @Override
  public boolean isAccountNonLocked() {
    return status != Status.L;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return status != Status.P;
  }

  @Override
  public boolean isEnabled() {
    return status == Status.A;
  }

}
