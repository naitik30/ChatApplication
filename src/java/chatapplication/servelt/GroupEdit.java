/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.servelt;

import chatapplication.Beans.DbUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@WebServlet(name = "GroupEdit", urlPatterns = {"/GroupEdit"})
public class GroupEdit extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String groupname = request.getParameter("grouphiddenname");
String url ;
            Connection con = (Connection) request.getServletContext().getAttribute("connection");
            DbUtils dbutils = new DbUtils();
            HttpSession s1 = request.getSession();
            String username = (String) s1.getAttribute("username");
            if (!username.isEmpty())
            {if (groupname.equals("editGroup")) {
                String newgroupname = request.getParameter("groupname");
                String oldgroupname = (String) s1.getAttribute("oldgroupname");
                System.out.println(oldgroupname);
                String userlist = request.getParameter("userlist").replaceAll("\\s","");
                String[] items = userlist.split(",");
                
                dbutils.updateGroupMember(con,items,newgroupname,oldgroupname);
                url = "admin.jsp";
                ArrayList groupList = (ArrayList) DbUtils.getGroupList(con);
                ArrayList usernameList = (ArrayList) dbutils.getUserList(con);
                request.setAttribute("groupList",groupList);
                request.setAttribute("usernameList",usernameList);
            } else {
                request.setAttribute("groupname", groupname);
                s1.setAttribute("oldgroupname", groupname);
                ArrayList usernameList = (ArrayList) dbutils.getUserList(con);
                request.setAttribute("usernameList", usernameList);
                ArrayList groupmemberList = (ArrayList) DbUtils.getGroupMember(con, groupname);
                String groupmember = (String) groupmemberList.get(0);
                int i = 1;
                if (groupmemberList.size() > 2) {
                    for (i = 1; i < groupmemberList.size() - 1; i++) {
                        groupmember = groupmember + "," + (String) groupmemberList.get(i);
                    }
                }
                if (groupmemberList.size() >= 2) {
                    groupmember = groupmember + "," + (String) groupmemberList.get(1);
                }
                request.setAttribute("groupmember", groupmember);
                url = "editgroup.jsp";
            }}
            else{
                url = "index.jsp";
                
            }
            RequestDispatcher view = request.getRequestDispatcher(url);
            view.forward(request, response);
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
            Logger.getLogger(GroupEdit.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GroupEdit.class.getName()).log(Level.SEVERE, null, ex);
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
