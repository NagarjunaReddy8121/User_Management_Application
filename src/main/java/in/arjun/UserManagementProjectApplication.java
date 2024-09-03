package in.arjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementProjectApplication.class, args);
	}

}
