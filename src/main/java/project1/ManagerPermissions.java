package project1;

public interface ManagerPermissions 
{
	void setReimbursementStatus(int reimId, String status);
	Employee getEmployeeInfo(int id);
	void createEmployee(Employee emp);
}
