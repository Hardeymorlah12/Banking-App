package com.hardeymorlah.Banking_App;

import com.hardeymorlah.Banking_App.config.AccountConfiguration;
import com.hardeymorlah.Banking_App.model.AccountUser;
import com.hardeymorlah.Banking_App.model.DTOs.Role;
import com.hardeymorlah.Banking_App.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankingAppApplication  implements CommandLineRunner {
	@Autowired
	private AccountUserService accountUserService;

	@Autowired
	private AccountConfiguration accountConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PasswordEncoder encoder = accountConfiguration.passwordEncoder();

		AccountUser adminUser = new AccountUser();
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setRole(Role.ADMIN);
		adminUser.setUsername("admin@gmail.com");
		adminUser.setPhoneNumber("08145032997");
		adminUser.setPassword(encoder.encode("password"));

		AccountUser exist = accountUserService.getAccountUserByUsername("admin@gmail.com").getBody();

		if( exist == null ){
			accountUserService.postAccountUser(adminUser);
		}

	}
}
