package gr.nothingness.backofficeusermanager.audit.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AuditRepository<T, ID> extends Repository<T, ID> {

  public Iterable<T> findAll();

  public Iterable<T> findAll(Sort var1);

  public Page<T> findAll(Pageable var1);

  public Iterable<T> findAllById(Iterable<ID> var1);

  public Optional<T> findById(ID var1);

  public T getById(ID var1);

  public boolean existsById(ID var1);

  public long count();

}
