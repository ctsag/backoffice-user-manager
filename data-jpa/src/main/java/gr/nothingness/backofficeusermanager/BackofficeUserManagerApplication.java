package gr.nothingness.backofficeusermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: OPTIONS verb for actuator paths?
//TODO: eTags and caching?

@SpringBootApplication
public class BackofficeUserManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackofficeUserManagerApplication.class, args);
  }

}
