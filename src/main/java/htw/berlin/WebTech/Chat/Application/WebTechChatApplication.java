package htw.berlin.WebTech.Chat.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories
public class WebTechChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebTechChatApplication.class, args);
	}

}
