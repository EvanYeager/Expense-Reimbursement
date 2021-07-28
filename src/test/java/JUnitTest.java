import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.Address;
import project1.ConnectionUtil;
import project1.Employee;
import project1.Manager;

public class JUnitTest 
{
	Manager testUser = new Manager("mr", "manager", new Address("testing", "facility"), "tester", "yepyep");
	
	@Test
	public void testConnection()
	{
		assertNotNull(ConnectionUtil.getConnection());
	}
	
	@Test
	public void testCreateEmployee() throws SQLException
	{
		Employee emp = new Employee("firstname", "lastname", new Address("city", "state"), "username", "password");
		testUser.createEmployee(emp);
		Connection connection = ConnectionUtil.getConnection();
		
		String query = "select firstname from personnel where username = 'username'";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		results.next();
		assertNotNull(results.getString("firstname"));
	}
	
	@Test
	public void testCreateManager() throws SQLException
	{
		Employee emp = new Manager("mr", "manager", new Address("city", "state"), "username", "password");
		testUser.createEmployee(emp);
		Connection connection = ConnectionUtil.getConnection();
		
		String query = "select * from personnel where lastname = 'manager'";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		results.next();
		// if manager column is true
		assertTrue(results.getInt("manager") == 1);
	}
	
	@Test
	public void testIsManager() throws SQLException
	{
		assertTrue(testUser.isManagerByLogin("admin", "adminpassword"));
	}
	
	@Test
	public void testIsNotManager() throws SQLException
	{
		assertFalse(testUser.isManagerByLogin("notadmin", "notadminpassword"));
	}
	
	@Test
	public void testLoginTrue() throws SQLException
	{
		assertTrue(testUser.checkLogin("username", "password"));
	}
	
	@Test
	public void testLoginFalse() throws SQLException
	{
		assertFalse(testUser.checkLogin("thisshouldntbeinthere", "thisdoesntexists"));
	}
	
	@Test
	public void testGetEmpName() throws SQLException
	{
		assertEquals(testUser.getEmployeeNameByID(31), "mr manager");
	}
	
	@Test
	public void testGetEmpNameDoesntExist() throws SQLException
	{
		assertEquals(testUser.getEmployeeNameByID(-1), "");
	}
	
	@Test
	public void testGetEmpNameIncorrect() throws SQLException
	{
		assertNotEquals(testUser.getEmployeeNameByID(31), "The name shouldn't be this");
	}
	
	@Test
	public void testSetReimStatus() throws SQLException
	{
		testUser.setReimbursementStatus(1, "approved");
		
		Connection connection = ConnectionUtil.getConnection();
		String query = "select status from reimbursement where id = 1";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		results.next();
		assertTrue(results.getString("status").equals("approved"));
	}
}
