package it.shiftlab.lawyer.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EntityScan("it.shiftlab.lawyer.jpa.entity")
@EnableJpaRepositories("it.shiftlab.lawyer.jpa.repository")
@ComponentScan(basePackages = {"it.shiftlab.lawyer"})
public class JpaConfig {
}
