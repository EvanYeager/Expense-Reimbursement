package project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	Manager util = new Manager();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		request.getRequestDispatcher("/Login.html").forward(request, response);
		
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");



        
        if (util.checkLogin(request.getParameter("username"), request.getParameter("password")))
        {
        	HttpSession session = request.getSession();
        	session.setAttribute("name", request.getParameter("username"));
        	response.sendRedirect("EmpHomepage.html");
        	if (session.getAttribute("name") != null) System.out.println("in login servlet, name is valid");
			else System.out.println("in login servlet, name is not valid.");
        }
        else
        {
        	response.sendRedirect("invalid.html");
        }

    }
}
