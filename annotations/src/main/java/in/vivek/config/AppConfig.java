package in.vivek.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"in.vivek", "in.company"})
public class AppConfig {

}
