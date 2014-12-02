/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jani-Meiju
 */
public class ClientList implements Serializable{
    


    private ArrayList<String> clientList = new ArrayList();

    public ArrayList<String> getClientList() {
        return clientList;
    }

    public void setClientList(String user) {
        clientList.add(user);
    }



}
