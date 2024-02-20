package in.vivek.beans;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class ReportService {
	//Field Injection
//	@Autowired
	// Identify by name
	//	@Qualifier("sqlDbDao")
//	@Qualifier("oracleDbDao")
	private ReportDao reportDao;

	//	public ReportService(ReportDao reportDao) {
	//		this.reportDao = reportDao;
	//	}
	//Setter Injection
//	@Autowired
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
		//Constructor Injection
		@Autowired
		public ReportService(ReportDao reportDao) {
			this.reportDao = reportDao;
		}

	public void generateReport() {
		reportDao.getData();
		System.out.println("Report generated");
	}

}
