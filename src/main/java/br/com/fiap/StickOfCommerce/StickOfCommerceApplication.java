package br.com.fiap.StickOfCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
    info = @Info(
        title = "StickOfCommerce",
        version = "v1",
        description = "API para um Mercado medieval",
        contact = @Contact(
            name = "Diego Bassalo",
            email = "diegobassalo1@gmail.com"
        )
    )
)
public class StickOfCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StickOfCommerceApplication.class, args);
	}

}
