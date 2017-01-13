/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.servelt;

import chatapplication.Beans.DbUtils;
import chatapplication.Beans.GroupChat;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author naitik
 */
public class Loginmanager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String loginType = null;
            DbUtils dbutils = new DbUtils();
            Connection con = (Connection) request.getServletContext().getAttribute("connection");
            loginType = request.getParameter("loginType");
            String url = "index.jsp";
            try {
                if (loginType.equals("signup")) {
                    String username = request.getParameter("email");
                    String password = request.getParameter("password");
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name");
                    String email = request.getParameter("email");
                    Statement stmt = con.createStatement();
                    if (username != null || password != null || first_name != null || last_name != null || email != null) {
                        String e = "select * from user_info where Email='" + email + "'";

                        ResultSet rs = stmt.executeQuery(e);
                        String message = null;
                        if (rs.next()) {
                            message = "You have Already Registered:";
                            System.out.println("TEST");
                        } else {
                            String em = "insert into user_info(firstname,lastname,username,password,email)" + "values('" + first_name + "','" + last_name + "','" + username + "','" + password + "','" + email + "')";
                            System.out.println(em);
                            stmt.executeUpdate(em);
                            message = "Registration Sucessfully";
                            url = "index.jsp";
                            request.setAttribute("newusermessage", message);
                        }

                    }
                }
                if (loginType.equals("login")) {
                    url = "homepage.jsp";
                    String username = request.getParameter("email");
                    String password = request.getParameter("password");

                    if (username != null || password != null) {
                        Statement stmt = con.createStatement();
                        String e = "select * from user_info where username='" + username + "'";
                        ResultSet rs = stmt.executeQuery(e);
                        String message = null;
                        HttpSession sessson = request.getSession(true);
                        if (username.equals("admin@admin.com") && password.equals("admin")) {
                            url = "admin.jsp";
                            ArrayList groupList = (ArrayList) DbUtils.getGroupList(con);
                            ArrayList usernameList = (ArrayList) dbutils.getUserList(con);
                            sessson.setAttribute("username", username);
                            sessson.setAttribute("password", password);
                            request.setAttribute("groupList", groupList);
                            request.setAttribute("usernameList", usernameList);
                        } else if (rs.next()) {
                            String qu = "select * from user_info where password='" + password + "'";
                            ResultSet rs1 = stmt.executeQuery(qu);
                            if (rs1.next()) {
                                url = "usergroup.jsp";

                                sessson.setAttribute("username", username);
                                sessson.setAttribute("password", password);
                                ArrayList groupList = new ArrayList();
                                groupList = (ArrayList) dbutils.getUserGroup(con, username);
                                request.setAttribute("groupList", groupList);
                            } else {
                                message = "Wrong password";
                                url = "index.jsp";
                            }
                        } else {
                            message = "Please enter correct Username and password";
                            url = "index.jsp";
                        }
                        request.setAttribute("message", message);
                    }
                }

                RequestDispatcher view = request.getRequestDispatcher(url);
                view.forward(request, response);

            } catch (Exception ex) {
                RequestDispatcher view = request.getRequestDispatcher(url);
                view.forward(request, response);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
