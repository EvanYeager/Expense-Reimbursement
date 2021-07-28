package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil 
{
	
	public Connection connect()
	{
		return ConnectionUtil.getConnection();
	}
	
	public int createEmployee(Employee obj)
	{
		Connection connection = connect();
		String query = "insert into personnel (firstname, lastname, city, state, manager, username, password) values (?, ?, ?, ?, ?, ?, ?)";
		try 
		{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, obj.getFirstName());
			statement.setString(2, obj.getLastName());
			statement.setString(3, obj.getAddress().getCity());
			statement.setString(4, obj.getAddress().getState());
			statement.setInt(5, obj instanceof Manager ? 1 : 0); // if employee passed in is of the manager class, set manager = 1, otherwise set it to 0
			statement.setString(6, obj.getUsername());
			statement.setString(7, obj.getPassword());
			
			return statement.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	// returns the full name of the employee, i.e. "Jeremy Clarkson"
	public String getEmployeeNameByID(int ID) throws SQLException
	{
		Connection connection = connect();
		String query = "select firstname, lastname from personnel where id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, ID);
		ResultSet results = statement.executeQuery();
		if (results.next())
		{
			return (results.getString("firstname") + " " + results.getString("lastname"));
		}
		return "";		
	}
	
	// public boolean checkLogin
	public boolean checkLogin(String username, String password) throws SQLException
	{
		Connection connection = connect();
		String query = "select * from personnel where username = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		return statement.executeQuery().next(); // returns if there is a record found
	}
	
	public boolean getIsManagerByLogin(String username, String password) throws SQLException
	{
		Connection connection = connect();
		String query = "select manager from personnel where username = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet results = statement.executeQuery();
		
		if (results.next())
		{
			return results.getInt("manager") == 0 ? false : true; // convert 0 / 1 to boolean
		}
		return false; // false if no record is found with this login
	}
	
	
	// create reimbursements
	
	
	
	public List<Reimbursement> getReimbursements() throws SQLException
	{
		/*
		 * might need to change this to convert reimbursements to json
		 */
		
		Connection connection = connect();
		String query = "select * from reimbursements where issuer_id = ?";	
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, LoginSession.getId());
		ResultSet results = statement.executeQuery();
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		while(results.next()) 
		{
			reimbursements.add(new Reimbursement(getEmployeeNameByID(LoginSession.getId()), results.getInt("expenseamount"), results.getString("notes"), results.getString("status")));
		}
		return reimbursements;
	}
	
	
	public void setReimbursementStatus(int id, String status) throws SQLException
	{
		Connection connection = connect();
		String query = "update reimbursement set status = ? where id = ?";	
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, status);
		statement.setInt(2, id);
		statement.executeUpdate();
	}
	

	public Employee getEmployeeInfo(String username) throws SQLException
	{
		Connection connection = connect();
		String query = "select * from personnel where username = ?";	
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		if (results.next())
		{
			return new Employee(results.getString("firstname"), results.getString("lastname"), new Address(results.getString("city"), results.getString("state")), results.getString("username"), results.getString("password"));
		}
		return null;
	}
	
	
//	public boolean changeEmployeeInfo()
//	don't know how to do this... I think it should go in the interface and the implementation is different for employee vs manager because the manager can change more information than the standard employee'
//	but also how would I specify what information to change through arguments? I might need to create several methods for each info changed
}
