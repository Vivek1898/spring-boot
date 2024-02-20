package in.vivek.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import in.vivek.AppConfig;
import in.vivek.beans.ReportService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ReportService reportService = context.getBean(ReportService.class);
		reportService.generateReport();
		
	}

}
