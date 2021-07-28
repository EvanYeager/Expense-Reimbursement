package project1;

import java.util.List;

public interface BasePermissions 
{
	String getEmployeeNameByID(int id);
	boolean checkLogin(String username, String password);
	boolean isManagerByLogin(String username, String password);
	List<Reimbursement> getReimbursements(String userUsername);
	Employee getEmployeeInfo(String username);
	int getIDByName(String username);

	void deleteReimbursement();
}
