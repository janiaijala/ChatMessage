/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import message.RequestChatMessage;
import message.RequestRegisterUserResponse;
/**
 *
 * @author Opiframe
 */
public class ChatServer2 {

    static ArrayList<ServerClientBackEnd> clients = new ArrayList();
    public static void main(String[] args) {
        try {
            //Start the server to listen port 3010
            ServerSocket server = new ServerSocket(3010);
            
            //Start to listen and wait connections
            while(true){
                //Wait here the client
                Socket temp = server.accept();
                
                ServerClientBackEnd backEnd = new ServerClientBackEnd(temp);
                clients.add(backEnd);
                Thread t = new Thread(backEnd);
                t.setDaemon(true);
                t.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void broadcastMessage(RequestChatMessage cm){
        
        if(cm.isIsPrivate()){
            for(ServerClientBackEnd temp: clients){
                if(cm.getPrivateName().equals(temp.getName())){
                    temp.sendMessage(cm);
                }
            }
        }
        else{
            for(ServerClientBackEnd temp: clients){
                temp.sendMessage(cm);
            }
        }
    }
    
    public static void sendUserList(){
        RequestRegisterUserResponse userList = new RequestRegisterUserResponse();
        for(ServerClientBackEnd temp: clients){
            userList.setName(temp.getName());
        }
        
        for(ServerClientBackEnd temp: clients){
            temp.sendMessage(userList);
        }
    }
}
