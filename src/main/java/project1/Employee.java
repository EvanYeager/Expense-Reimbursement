package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employee implements BasePermissions
{
	protected String firstName;
	protected String lastName;
	protected Address address;
	protected String username;
	protected String password;
	
	public Employee()
	{
		super();
	}
	
	public Employee(String firstName, String lastName, Address address, String username, String password) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.username = username;
		this.password = password;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * gets the full name of an employee
	 * 
	 * @param id the primary key of the employee
	 * 
	 * @return full employee name separated by a space i.e. "Jeremy Clarkson"
	 */
	@Override
	public String getEmployeeNameByID(int id) {
		String query = "select firstname, lastname from personnel where id = ?";
		try (Connection connection = ConnectionUtil.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			
			if (results.next()) return (results.getString("firstname") + " " + results.getString("lastname"));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * checks if the login belongs to an employee in the database
	 * 
	 * @param username the username
	 * @param password the password
	 * 
	 * @return true if the login belongs to an employee, otherwise false
	 */
	@Override
	public boolean checkLogin(String username, String password) {
		String query = "select * from personnel where username = ? and password = ?";
		try (Connection connection = ConnectionUtil.getConnection()) 
		{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			return statement.executeQuery().next(); // returns true if there is a record found
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * checks if the user is a manager
	 * 
	 * @param username the username
	 * @param password the password
	 * 
	 * @return true if the user has manager permissions, otherwise false
	 */
	@Override
	public boolean isManagerByLogin(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "select manager from personnel where username = ? and password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			
			if (results.next())
			{
				return results.getInt("manager") == 0 ? false : true; // convert 0 / 1 to boolean
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false; // false if no record is found with this login
	}



	/**	
	 * Queries the database for all reimbursements that belong to the current user.
	 *
	 * @param userUsername the username of the user to which the reimbursements belong
	 * @return List of reimbursement objects
	 */
	@Override
	public List<Reimbursement> getReimbursements(String userUsername) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "select * from reimbursement where issuer_fk = ?";	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, getIDByName(userUsername));
			ResultSet results = statement.executeQuery();
			
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			while(results.next()) 
			{
				reimbursements.add(new Reimbursement(userUsername, results.getInt("expenseamount"), results.getString("notes"), results.getString("status")));
			}
			return reimbursements;
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * returns an employee object reflecting the current user logged in
	 * @return an Employee object that contains the data of the user logged in 
	 */
	@Override
	public Employee getEmployeeInfo(String username) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "select * from personnel where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				return new Employee(results.getString("firstname"), results.getString("lastname"), new Address(results.getString("city"), results.getString("state")), results.getString("username"), results.getString("password"));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getIDByName(String username) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "select id from personnel where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				return results.getInt("id");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void deleteReimbursement() {

	}


}
