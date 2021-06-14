package gr.nothingness.backofficeusermanager.audit.entities;

import static javax.persistence.TemporalType.TIMESTAMP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadmingroup_aud")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeGroupAudit extends BaseAudit {

  @Column(name = "group_id")
  private Long groupId;

  @Column(name = "group_name")
  @Size(max = 64)
  private String groupName;

  @Column(name = "group_owner")
  private Long groupOwner;

  @Column(name = "cr_date")
  @Temporal(TIMESTAMP)
  private Date groupCreationDate;

}
