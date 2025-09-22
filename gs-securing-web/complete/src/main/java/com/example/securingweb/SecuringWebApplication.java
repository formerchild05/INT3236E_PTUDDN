package com.example.securingweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecuringWebApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(SecuringWebApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			User u1 = new User();
			u1.setUsername("abc");
			u1.setPassword(encoder.encode("123")); // encode mật khẩu
			u1.setRole("ROLE_USER");

			User u2 = new User();
			u2.setUsername("admin");
			u2.setPassword(encoder.encode("123"));
			u2.setRole("ROLE_ADMIN");

			userRepository.save(u1);
			userRepository.save(u2);
		};
	}
}
