package gr.nothingness.backofficeusermanager.controllers;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import gr.nothingness.backofficeusermanager.repositories.BackofficeUserRepository;
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

//TODO: password should not be returned back to the requestor
//TODO: perhaps the username can be added as a REST path?
//TODO: search endpoint, support find by last name

@RestController
@RequestMapping("/api/v1/users")
public class BackofficeUserController {

  @Autowired
  private BackofficeUserRepository repository;

  @GetMapping
  public List<BackofficeUser> list() {
    return repository.findAll();
  }

  //TODO: handle non existent user (404, no body)
  @GetMapping("{id}")
  public Optional<BackofficeUser> get(@PathVariable Long id) {
    return repository.findById(id);
  }

  //TODO: Returned user's permission must be an empty list instead of null
  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public BackofficeUser create(@RequestBody BackofficeUser user) {
    return repository.saveAndFlush(user);
  }

  //TODO: Handle non existent user (404 Not Found)
  //TODO: Return 204 No Content
  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  //TODO: How do you associate user with permission?
  //TODO: Handle non existent user (create new user)
  @PutMapping("{id}")
  public BackofficeUser update(@PathVariable Long id, @RequestBody BackofficeUser user) {
    BackofficeUser existingUser = repository.getOne(id);

    BeanUtils.copyProperties(user, existingUser, "id");

    return repository.saveAndFlush(existingUser);
  }

  //TODO: PATCH verb

}
