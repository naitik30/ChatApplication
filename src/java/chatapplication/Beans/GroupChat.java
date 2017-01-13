/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.Beans;

import java.util.ArrayList;

/**
 *
 * @author naitik
 */
public class GroupChat {
     ArrayList messageList = new ArrayList();
     ArrayList userList = new ArrayList();
     String groupName ;
     String groupId;

    public ArrayList getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList messageList) {
        this.messageList = messageList;
    }

    public ArrayList getUserList() {
        return userList;
    }

    public void setUserList(ArrayList userList) {
        this.userList = userList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
     
    public  GroupChat(String groupName, String groupId)
    {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    
    public boolean addUser(String username)
    {
        userList.add(username);
        return true;
    }
    
    public boolean removeUser(String username)
    {
        if (userList.contains(username))
        {
             userList.remove(username);
             return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean addmessage(String message,String sender)
    {
//        if (userList.contains(sender))
//        {
            Message mes = new Message(sender,message);
            messageList.add(mes);
            return true;
//        }
//        else
//        {
//            return false;
//        }
    }
}
