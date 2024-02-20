package in.company.dao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	public UserDao() {
		System.out.println("UserDao constructor");
	}

	public String getUser() {
		return "Vivek";
	}

}
