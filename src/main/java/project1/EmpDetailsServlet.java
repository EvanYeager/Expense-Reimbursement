package project1;

import java.io.IOException;
import org.json.simple.JSONObject;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpDetailsServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null)
        {
            System.out.println("username not found in session");
            return;
        }

        Manager util = new Manager();
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Employee details = util.getEmployeeInfo((String)session.getAttribute("name"));
        
        JSONObject data = new JSONObject();
        		data.put("firstname", details.getFirstName());
        		data.put("lastname", details.getLastName());
        		data.put("city", details.getAddress().getCity());
        		data.put("state", details.getAddress().getState());
        		data.put("username", details.getUsername());
        		data.put("password", details.getPassword());

        out.println(mapper.writeValueAsString(data));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
