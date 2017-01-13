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
public class Group {
    String groupName;
    ArrayList userList;

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
    
    
}
