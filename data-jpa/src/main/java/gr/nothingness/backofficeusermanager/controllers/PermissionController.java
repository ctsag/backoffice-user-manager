package gr.nothingness.backofficeusermanager.controllers;

import gr.nothingness.backofficeusermanager.entities.Permission;
import gr.nothingness.backofficeusermanager.repositories.PermissionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

  @Autowired
  private PermissionRepository repository;

  @GetMapping
  public List<Permission> list() {
    return repository.findAll();
  }

  @GetMapping("{name}")
  public Optional<Permission> get(@PathVariable String name) {
    return repository.findById(name);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Permission create(@RequestBody Permission permission) {
    return repository.saveAndFlush(permission);
  }

  @DeleteMapping("{name}")
  public void delete(@PathVariable String name) {
    repository.deleteById(name);
  }

  @PutMapping("{name}")
  public Permission update(@PathVariable String name, @RequestBody Permission permission) {
    Permission existingPermission = repository.getOne(name);

    BeanUtils.copyProperties(permission, existingPermission, "name");

    return repository.saveAndFlush(existingPermission);
  }

}
