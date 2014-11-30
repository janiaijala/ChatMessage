/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import message.MessageBase;
import message.RequestChatMessage;
import message.RequestRegisterUser;

/**
 *
 * @author Opiframe
 */
public class ServerClientBackEnd implements Runnable{
    
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String name;//Store name here
    
    public ServerClientBackEnd(Socket sock){
        socket = sock;
    }
    
    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        
            //Start to wait data
            while(socket.isConnected() && !socket.isClosed()){
                Object object = input.readObject();
                if(object instanceof RequestChatMessage){
                    RequestChatMessage cm = (RequestChatMessage)object;
                    ChatServer2.broadcastMessage(cm);
                }
                else if(object instanceof RequestRegisterUser){
                    RequestRegisterUser rru = (RequestRegisterUser)object;
                    name = rru.getUserName();
                    ChatServer2.sendUserList();
                }
            }
            
            output.close();
            input.close();
            ChatServer2.clients.remove(this);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void sendMessage(MessageBase data){
        try {
            output.writeObject(data);
            output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getName(){
        return name;
    }
}
