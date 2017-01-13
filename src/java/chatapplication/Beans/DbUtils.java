/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.Beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author naitik
 */
public class DbUtils {

    public static ArrayList getGroupList(Connection con) throws SQLException {
        ArrayList<Group> groupList = new ArrayList<Group>();
        try {

            Statement stmt = con.createStatement();
            String e = "select * from group_chat";
            ResultSet rs = stmt.executeQuery(e);
            while (rs.next()) {
                String groupname = rs.getString("groupname");
                int id = rs.getInt("Id");

                String el = "select username from user_info where Id in(select user_id from group_user where group_id=" + id + ")";
                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(el);
                ArrayList usernameList = new ArrayList();
                while (rs1.next()) {
                    usernameList.add(rs1.getString("username"));
//                    System.out.ln(usernameList);
                }

                Group g1 = new Group();
                g1.setGroupName(groupname);
                g1.setUserList(usernameList);
                groupList.add(g1);

//                System.out.println("TEEEE");
            }

//            System.out.println(groupList);
            return groupList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupList;
    }

    public static ArrayList getUserList(Connection con) throws SQLException {

        ArrayList usernameList = new ArrayList();
        Statement stmt = con.createStatement();
        String e = "select username from user_info";
        ResultSet rs = stmt.executeQuery(e);
        while (rs.next()) {
            usernameList.add(rs.getString("username"));
        }
        return usernameList;
    }

    public static ArrayList getGroupMember(Connection con, String gorupname) throws SQLException {
        ArrayList groupmember = new ArrayList();
        Statement stmt = con.createStatement();
        String e = "select username from user_info where Id in(select user_id from group_user where group_id in(select Id from group_chat where groupname='" + gorupname + "'))";
//        System.out.println("");
        ResultSet rs = stmt.executeQuery(e);
        while (rs.next()) {
            groupmember.add(rs.getString("username"));
        }
        return groupmember;

    }

    public static void updateGroupMember(Connection con, String[] itemList, String newGroupName, String oldgroupname) throws SQLException {

        Statement stmt = con.createStatement();
        String e = "UPDATE group_chat SET groupname='" + newGroupName + "'WHERE groupname='" + oldgroupname + "'";
        int result = stmt.executeUpdate(e);
        e = "select Id from group_chat where groupname='" + newGroupName + "'";
        ResultSet rs = stmt.executeQuery(e);
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt("Id");
        }
        e = "DELETE FROM group_user WHERE group_id =" + group_id;
        result = stmt.executeUpdate(e);
        System.out.println(itemList);
        for (int i = 0; i < itemList.length; i++) {
            Statement stmt1 = con.createStatement();
            e = "select Id from user_info where username='" + itemList[i] + "'";
            ResultSet rs1 = stmt1.executeQuery(e);
            int user_id = 0;
            if (rs1.next()) {
                user_id = rs1.getInt("Id");
            }
            e = "insert into group_user(group_id,user_id)" + "values(" + group_id + "," + user_id + ")";
            stmt1.executeUpdate(e);
        }

    }

    public static void deleteGroup(Connection con, String groupname) throws SQLException {
        Statement stmt = con.createStatement();
        String e = "select Id from group_chat where groupname='" + groupname + "'";
        ResultSet rs = stmt.executeQuery(e);
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt("Id");
        }
        e = "DELETE FROM group_user WHERE group_id =" + group_id;
        int result = stmt.executeUpdate(e);
        e = "DELETE FROM group_chat WHERE Id =" + group_id;
        result = stmt.executeUpdate(e);

    }

    public static void createGroup(Connection con, String[] itemList, String GroupName) throws SQLException {
        Statement stmt = con.createStatement();
        String e = "insert into group_chat (groupname)" + "values('" + GroupName + "')";
        PreparedStatement prest = con.prepareStatement(e, Statement.RETURN_GENERATED_KEYS);
        try {
            prest.executeUpdate();

        } catch (Exception ex) {
        }
        ResultSet rs = prest.getGeneratedKeys();
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt(1);
        }
//        System.out.println(group_id);
        for (int i = 0; i < itemList.length; i++) {
            Statement stmt1 = con.createStatement();
            e = "select Id from user_info where username='" + itemList[i] + "'";
            ResultSet rs1 = stmt1.executeQuery(e);
            int user_id = 0;
            if (rs1.next()) {
                user_id = rs1.getInt("Id");
            }
            e = "insert into group_user(group_id,user_id)" + "values(" + group_id + "," + user_id + ")";
            stmt1.executeUpdate(e);
        }
    }

    public static ArrayList getUserGroup(Connection con, String username) throws SQLException {
        ArrayList groupList = new ArrayList();
        Statement stmt = con.createStatement();
        String e = "select groupname from group_chat where Id in(select group_id from group_user where user_id in(select Id from user_info where username='" + username + "'))";
//        System.out.println(e);
        ResultSet rs = stmt.executeQuery(e);
        while (rs.next()) {
            groupList.add(rs.getString("groupname"));
        }
        return groupList;
    }

    public static ArrayList getMessageList(Connection con, String groupname) throws SQLException {

        ArrayList messages = new ArrayList();
        Statement stmt = con.createStatement();

        String e = "select Id from group_chat where groupname='" + groupname + "'";
        ResultSet rs = stmt.executeQuery(e);
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt("Id");
        }
        String mesquery = "select * from message where group_id="+group_id+" ORDER BY time_stemp DESC";

        ResultSet mesrs = stmt.executeQuery(mesquery);
        int t = 0;
        while (mesrs.next()) {
            ArrayList mesList = new ArrayList();
            String mesg = mesrs.getString("message");
            String sender = mesrs.getString("sender");
            mesList.add(mesg);
            mesList.add(sender);
            messages.add(mesList);
            t++;
        }
        return messages;
    }

    public static JSONObject messageSend(Connection con, String mes, String username, String groupname) throws SQLException {

        Statement stmt = con.createStatement();

        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
        String e = "select Id from group_chat where groupname='" + groupname + "'";
        ResultSet rs = stmt.executeQuery(e);
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt("Id");
        }
        e = "select firstname,lastname from user_info where username='" + username + "'";
        rs = stmt.executeQuery(e);
        String user ="";
        if (rs.next()) {
            user = rs.getString("firstname")+ " " +rs.getString("lastname");
        }
        
        String em = "insert into message(message,sender,time_stemp,group_id)" + "values('" + mes + "','" + user + "','" + timestamp + "',"+group_id+")";
        stmt.executeUpdate(em);
        ArrayList messages = new ArrayList();
        String mesquery = "select * from message where group_id="+group_id+" ORDER BY time_stemp DESC";
        ResultSet mesrs = stmt.executeQuery(mesquery);
        JSONObject json = new JSONObject();
        JSONArray jsonMessages = new JSONArray();
        while (mesrs.next()) {
            ArrayList mesList = new ArrayList();
            JSONObject jsonMsgList = new JSONObject();
            String mesg = mesrs.getString("message");
            String sender = mesrs.getString("sender");
            mesList.add(mesg);
            mesList.add(sender);
            messages.add(mesList);
            jsonMsgList.put("mesg", mesg);
            jsonMsgList.put("sender", sender);
            jsonMessages.add(jsonMsgList);
        }
        json.put("jsonArray", jsonMessages);
        return json;
    }
    
    public static JSONObject messageRead(Connection con, String groupname) throws SQLException
    {
         Statement stmt = con.createStatement();

        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
        String e = "select Id from group_chat where groupname='" + groupname + "'";
        ResultSet rs = stmt.executeQuery(e);
        int group_id = 0;
        if (rs.next()) {
            group_id = rs.getInt("Id");
        }
        ArrayList messages = new ArrayList();
        String mesquery = "select * from message where group_id="+group_id+" ORDER BY time_stemp DESC";
        ResultSet mesrs = stmt.executeQuery(mesquery);
        JSONObject json = new JSONObject();
        JSONArray jsonMessages = new JSONArray();
        while (mesrs.next()) {
            ArrayList mesList = new ArrayList();
            JSONObject jsonMsgList = new JSONObject();
            String mesg = mesrs.getString("message");
            String sender = mesrs.getString("sender");
            mesList.add(mesg);
            mesList.add(sender);
            messages.add(mesList);
            jsonMsgList.put("mesg", mesg);
            jsonMsgList.put("sender", sender);
            jsonMessages.add(jsonMsgList);
        }
        json.put("jsonArray", jsonMessages);
        return json;
    }
}
