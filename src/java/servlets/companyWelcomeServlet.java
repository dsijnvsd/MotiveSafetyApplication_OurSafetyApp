/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.LoginDB;
import dataaccess.ManualDB;
import domain.Company;
import domain.Logins;
import domain.Manual;
import domain.Typelibrary;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Company welcome page management
 * @author 809968
 * @version 1.0
 */
public class companyWelcomeServlet extends HttpServlet {

/**
 * In charge of fetching data to the page
 * @param request Http Servlet Request
 * @param response Http Servlet Response
 * @throws ServletException Servlet Exception
 * @throws IOException  Input/Output Exception
 */   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        /**
         * Minor change here, removes hard coding on userID.*
         */
        // int userID = 2;
        int userID = (Integer) session.getAttribute("userID");
        Logins logins = new Logins();
        Company company = logins.getCompanyID();
        String logout = request.getParameter("action");
        String equipment = request.getParameter("equipment");
        String employeeDraft = request.getParameter("employeeDraft");
        String manual = request.getParameter("manual");
        LoginDB logindb = new LoginDB();

        try {
            List<Logins> loginList = logindb.getAll();
            for (Logins login : loginList) {
                if (login.getUserId() == userID) {
                    logins = login;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(companyWelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("companyName", logins.getCompanyID().getDescription());

        try {
            if (company != null) {
                response.sendRedirect("company");
                return;
            } else if (equipment != null) {
                response.sendRedirect("equipmentmanager");
                return;
            } else if (employeeDraft != null) {
                response.sendRedirect("employee");
                return;
            } else if (manual != null) {
                response.sendRedirect("manual");
                return;
            } else if (logout != null) {
                session.invalidate();
                session = request.getSession();
                response.sendRedirect("login");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(companyWelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/companyWelcome.jsp").forward(request, response);

    }

/**
 * In charge of actions in welcome page
 * @param request Http Servlet Request
 * @param response Http Servlet Response
 * @throws ServletException Servlet Exception
 * @throws IOException  Input/Output Exception
 */    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/companyWelcome.jsp").forward(request, response);

    }
}
