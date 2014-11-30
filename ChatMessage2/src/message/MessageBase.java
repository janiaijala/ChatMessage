package message;

import java.io.Serializable;
/**
 * Base class for all messages delivered between the chat client/server.
 * All classes in message package should inherit this class.
 * @author Markus Veijola
 * @version 0.0.1
 */
public abstract class MessageBase implements Serializable{
    
    private String userName;
    /**
     * Returns the user name of chat client
     * @return String representing the username
     */
    public String getUserName() {
        return userName;
    }
   
    /**
     * Sets the username of chat client. Has to be at least 1 character wide.
     * Otherwise throws 
     * @param userName 
     * @throws message.MessageException if username is too short
     */
    public void setUserName(String userName) throws MessageException {
        
        if(!userName.isEmpty()){
            this.userName = userName;
        }
        else{
            throw new MessageException("Too short username. Must be > 0");
        }
    }
    
}
