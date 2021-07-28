package project1;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@mydbinstance1.cuf0uyntbslo.us-east-1.rds.amazonaws.com:1521:EMPDB",
					"admin",
					"Dloopoovoodoo2");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
