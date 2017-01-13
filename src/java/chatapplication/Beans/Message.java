/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication.Beans;

/**
 *
 * @author naitik
 */
public class Message {
    String sender;
    String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
    

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
