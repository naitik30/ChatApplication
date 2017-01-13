/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.servelt;
import chatapplication.Beans.GroupChat;
import chatapplication.socket.ChatServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
/**
 *
 * @author naitik
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

public class MyAppServletContextListener 
                        implements ServletContextListener {

        @Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");	
//                ChatServer ob=new ChatServer();
             ArrayList groupList = new ArrayList();
            ServletContext service = arg0.getServletContext();
            GroupChat group1 = new GroupChat("first","1");
            groupList.add(group1);
            service.setAttribute("groupList", groupList);
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapplication", "root", "test");
            } catch (SQLException ex) {
                Logger.getLogger(Loginmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
            service.setAttribute("connection",con);
//                   
	}
}
