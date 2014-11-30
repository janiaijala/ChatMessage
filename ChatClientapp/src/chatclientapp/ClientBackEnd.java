/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclientapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import message.MessageException;
import message.RequestChatMessage;
import message.RequestRegisterUser;
import message.RequestRegisterUserResponse;

/**
 *
 * @author Opiframe
 */
public class ClientBackEnd implements Runnable{
    
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private FXMLDocumentController controller;
    public ClientBackEnd(FXMLDocumentController controller){
        this.controller = controller;
    }
    
    public void connectToServer(String userName) throws IOException{
        clientSocket = new Socket("localhost",3010);
    }
    
    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
            RequestRegisterUser tempUser = new RequestRegisterUser();
            try {
                tempUser.setUserName(controller.getName());
            } catch (MessageException ex) {
                ex.printStackTrace();
            }
            output.writeObject(tempUser);
            output.flush();
            
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        //read and write from socket until user closes the app
        while(true){
            try {
                handleResponses();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
    public void sendMessage(RequestChatMessage cm){
        
        try {
            output.writeObject(cm);
            output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void handleResponses() throws IOException, ClassNotFoundException{
        final Object object = input.readObject();
        
        if(object instanceof RequestChatMessage){
            
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    RequestChatMessage temp = (RequestChatMessage)object;
                    controller.updateTextArea(temp);
                }
            });
        }
        else if(object instanceof RequestRegisterUserResponse){
            RequestRegisterUserResponse rru = (RequestRegisterUserResponse)object;
            final ObservableList<String> names = FXCollections.observableArrayList(rru.getUserList());
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    controller.setUserNameList(names);
                }
            });
        }
    }
    
}
