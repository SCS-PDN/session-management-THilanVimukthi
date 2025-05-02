import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final HashMap<String, String> users = new HashMap<>();

    public void init() {
        users.put("Thilan", "pass1");
        users.put("Vimukthi", "pass2");
        users.put("admin", "admin123");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {

            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60);
            response.addCookie(userCookie);


            response.sendRedirect("DashboardServlet");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Invalid username or password!</h3>");
            response.getWriter().println("<a href='login.html'>Try Again</a>");
        }


    }
}
