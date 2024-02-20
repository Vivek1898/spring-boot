package in.vivek.beans;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;

@Repository("sqlDbDao")
//Specify this bean as primary if there are multiple beans of the same type
@Primary
public class SqlDbDao implements ReportDao {

	public SqlDbDao() {
		System.out.println("SqlDbDao constructor");
	}

	@Override
	public void getData() {
		System.out.println("Getting data from SQL DB");
	}

}
