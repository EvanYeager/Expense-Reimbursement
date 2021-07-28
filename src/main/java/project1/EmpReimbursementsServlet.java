package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpReimbursementsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Employee emp = new Employee("test", "test", new Address("test", "test"), "username", "password"); // TODO need to change this with the logged in user
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<Reimbursement> reims = emp.getReimbursements((String) session.getAttribute("name"));

		out.println(mapper.writeValueAsString(reims));
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

    }
}
