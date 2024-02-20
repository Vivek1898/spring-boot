package in.vivek.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"in.vivek", "in.company"})
public class AppConfig {
	
	public AppConfig() {
		System.out.println("AppConfig constructor");
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}

} 
