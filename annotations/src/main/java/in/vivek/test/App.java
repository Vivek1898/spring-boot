package in.vivek.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import in.vivek.config.AppConfig;

public class App {
	public static void main(String[] args) {
		System.out.println("Main method started");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
	}
}
