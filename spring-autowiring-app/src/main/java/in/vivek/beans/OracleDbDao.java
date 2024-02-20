package in.vivek.beans;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// Below annotation is used to tell spring that this class is a bean and it should be instantiated
//DI injection
// To Remove the ambiguity, we can use @Repository, @Service, @Component
// we are finding the bean by type and not by name
//@Repository("reportDao")
@Repository("oracleDbDao")
public class OracleDbDao implements ReportDao {

	public OracleDbDao() {
		System.out.println("OracleDbDao constructor");
	}

//	@Override
	public void getData() {
		System.out.println("Getting data from Oracle DB");
	}

}
