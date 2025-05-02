import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        response.setContentType("text/html");


        if (username != null) {
            response.getWriter().println("<h2>Welcome, " + username + "!</h2>");
            response.getWriter().println("<a href='logout'>Logout</a>");
        } else {
            response.getWriter().println("<h3>Access Denied. Please <a href='login.html'>login</a>.</h3>");
        }

        if (username != null) {

            List<Course> courseList = new ArrayList<>();
            courseList.add(new Course("CSC3103", "Server Side Web programing", "Mr.Isuru"));
            courseList.add(new Course("CSC3093", "OOAD", "Prof.Saluka"));
            courseList.add(new Course("CSC3112", "Software Engineering", "Dr.Ruwanthini"));
            request.setAttribute("courses", courseList);

            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);

        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Access Denied. Please <a href='login.html'>login</a>.</h3>");
        }

        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", message);
        }

        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);

    }
}
