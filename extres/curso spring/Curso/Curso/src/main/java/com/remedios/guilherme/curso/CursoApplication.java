package com.remedios.guilherme.curso;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.remedios.guilherme.curso.usuarios", "com.remedios.guilherme.curso.produto"})
@EnableJpaRepositories(basePackages = "com.remedios.guilherme.curso")
public class CursoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CursoApplication.class, args);
    }
}



