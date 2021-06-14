package gr.nothingness.backofficeusermanager.audit.repositories;

import gr.nothingness.backofficeusermanager.audit.entities.BaseAudit;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AuditRepository<T extends BaseAudit> extends Repository<T, Long> {

  public Iterable<T> findAll();

  public Iterable<T> findAll(Sort var1);

  public Page<T> findAll(Pageable var1);

  public Iterable<T> findAllById(Iterable<Long> var1);

  public Optional<T> findById(Long var1);

  public T getById(Long var1);

  public boolean existsById(Long var1);

  public long count();

}
