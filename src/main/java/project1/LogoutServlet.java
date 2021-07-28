package project1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
        response.setContentType("text/html");
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("this is in doGet()");
//		try {
//			response.sendRedirect("Login.html");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		System.out.println("this is in doPost()");
	}
}