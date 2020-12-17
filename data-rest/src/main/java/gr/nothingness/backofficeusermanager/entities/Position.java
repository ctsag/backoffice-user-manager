package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminposition")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Position {

  @Column(name = "position_id")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter private String id;

  @Column(name = "position_name")
  @NotNull @Size(max = 64)
  @Getter @Setter private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_position_id")
  @Getter @Setter private Position parentPosition;

  @OneToMany(mappedBy = "parentPosition")
  @Getter @Setter private List<Position> childPositions;

  @OneToMany(mappedBy = "position")
  @JsonIgnore
  @Getter @Setter private List<BackofficeUser> users;

}
