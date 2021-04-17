package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chels
 */
@WebServlet(name = "ErrorSevlet", urlPatterns = {"/ErrorSevlet"})
public class ErrorSevlet extends HttpServlet {

    /**
     *
     * @param request takes in request
     * @param response takes in response.
     * @throws ServletException throws ServletException 
     * @throws IOException throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    /**
     *
     * @param request takes in request
     * @param response takes in response.
     * @throws ServletException throws ServletException 
     * @throws IOException throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

}
