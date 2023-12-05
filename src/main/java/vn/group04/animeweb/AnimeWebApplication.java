package vn.group04.animeweb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Anime Web Project"
				, description = "Web  này được tạo ra với mục đích học tập"
				,version = "1.0.0"
		)
)
public class AnimeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeWebApplication.class, args);
	}

}
