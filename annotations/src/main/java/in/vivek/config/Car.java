package in.vivek.config;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype")
public class Car {
	
	public Car() {
		System.out.println("Car constructor");
	}

}
