package spring_payment_app;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		PaymentService ps = context.getBean(PaymentService.class);
		System.out.println("Main: " + ps);
		ps.doPayment(100.00);
	}
};

