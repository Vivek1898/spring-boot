package in.vivek.beans;

public class test {
	public static void main(String[] args) {
		ReportService reportService = new ReportService(new SqlDbDao());
		reportService.generateReport();
	}

}
