import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId=request.getParameter("courseId");
        HttpSession session = request.getSession(false);

        if (session != null && courseId != null) {

            List<Course> allCourses = new ArrayList<>();
            allCourses.add(new Course("CSC3103", "Server Side Web programing", "Mr.Isuru"));
            allCourses.add(new Course("CSC3093", "OOAD", "Prof.Saluka"));
            allCourses.add(new Course("CSC3112", "Software Engineering", "Dr.Ruwanthini"));

            Course selectedCourse = null;
            for (Course c : allCourses) {
                if (c.getCourseId().equals(courseId)) {
                    selectedCourse = c;
                    break;
                }
            }

            if (selectedCourse != null) {
                List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
                if (enrolled == null) {
                    enrolled = new ArrayList<>();
                }


                boolean alreadyEnrolled = enrolled.stream()
                        .anyMatch(c -> c.getCourseId().equals(courseId));

                if (!alreadyEnrolled) {
                    enrolled.add(selectedCourse);
                    session.setAttribute("enrolledCourses", enrolled);
                    response.sendRedirect("DashboardServlet?message=Enrolled+in+" + selectedCourse.getCourseName());
                } else {
                    response.sendRedirect("DashboardServlet?message=Already+enrolled+in+" + selectedCourse.getCourseName());
                }
            } else {
                response.sendRedirect("DashboardServlet?message=Invalid+course+ID");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }
}

