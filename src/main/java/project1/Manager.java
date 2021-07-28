package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Employee implements ManagerPermissions
{

	public Manager(String firstName, String lastName, Address address, String username, String password) {
		super(firstName, lastName, address, username, password);
	}
	
	public Manager()
	{
		super();
	}

	/**
	 * Sets the "status" field on the specified reimbursement
	 * @param reimId the primary key of the reimbursement to change
	 * @param status the status of the reimbursement represented as a string.
	 * Acceptable statuses are: "pending", "accepted", "denied".
	 */
	@Override
	public void setReimbursementStatus(int reimId, String status) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "update reimbursement set status = ? where id = ?";	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, status);
			statement.setInt(2, reimId);
			statement.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * gets all database information belonging to the employee with the specified ID
	 * @param id the primary key of the employee
	 * @return an employee object that holds the data for the queried employee
	 */
	@Override
	public Employee getEmployeeInfo(int id) {
		try (Connection connection = ConnectionUtil.getConnection())
		{
			String query = "select * from personnel where id = ?";	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
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

	/**
	 * inserts a new employee in the personnel table.
	 * @param emp an object that is of the Employee class that contains the necessary data. 
	 * If the object is a Manager class, the "manager" field will be set accordingly
	 */
	@Override
	public void createEmployee(Employee emp) {
		String query = "insert into personnel (firstname, lastname, city, state, manager, username, password) values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, emp.getFirstName());
			statement.setString(2, emp.getLastName());
			statement.setString(3, emp.getAddress().getCity());
			statement.setString(4, emp.getAddress().getState());
			statement.setInt(5, emp instanceof Manager ? 1 : 0); // if employee passed in is of the manager class, set manager = 1, otherwise set it to 0
			statement.setString(6, emp.getUsername());
			statement.setString(7, emp.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
