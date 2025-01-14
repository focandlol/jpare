package focandlol.simplecarjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleCarJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleCarJpaApplication.class, args);
    }

}
