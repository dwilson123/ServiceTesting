package org.wilson.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilsona on 6/13/16.
 */
public class UserMessages {

    private List<UserMessage> messages;

    public UserMessages(){
        this.messages = new ArrayList<>();
    }

    public void addMessage(UserMessage message){
        this.messages.add(message);
    }

}
