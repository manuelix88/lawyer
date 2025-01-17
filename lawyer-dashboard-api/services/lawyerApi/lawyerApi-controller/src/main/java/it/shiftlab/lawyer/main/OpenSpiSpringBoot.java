package it.shiftlab.lawyer.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenSpiSpringBoot implements CommandLineRunner {
    public static void main(String[] args) throws Exception {
        new SpringApplication(OpenSpiSpringBoot.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
