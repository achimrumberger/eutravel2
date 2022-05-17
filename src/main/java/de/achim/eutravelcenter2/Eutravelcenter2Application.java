package de.achim.eutravelcenter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;

@SpringBootApplication
@TypeHint(typeNames = {"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl",
		"org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl"})
public class Eutravelcenter2Application {

	public static void main(String[] args) {
		SpringApplication.run(Eutravelcenter2Application.class, args);
	}

}
