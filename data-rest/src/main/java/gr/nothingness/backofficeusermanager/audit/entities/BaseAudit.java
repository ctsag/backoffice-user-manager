package gr.nothingness.backofficeusermanager.audit.entities;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class BaseAudit {

  @Column(name = "aud_order")
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "aud_id")
  private Long issuerId;

  @Column(name = "aud_time")
  @Temporal(TIMESTAMP)
  private Date auditDate;

  @Column(name = "aud_op")
  private String operation;

}
