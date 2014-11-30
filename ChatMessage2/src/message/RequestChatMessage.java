/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

/**
 * This request is send by client to server to broadcast a new message to other
 * participants. If private flag is set, then message is sent to recipient 
 * defined in privateName member. User can also define a font size, color and
 * family. Font size is restricted to 12-25 pixels.
 * 
 * @author Markus Veijola
 * @version 0.0.1
 */
public class RequestChatMessage extends MessageBase{
    
    private int fontSize;
    private String fontFamily;
    private boolean isPrivate;
    private String privateName;
    private String messageColor;
    private String chatMessage;
    private final int MAX_FONT_SIZE = 25;
    private final int MIN_FONT_SIZE = 12;
    
    public RequestChatMessage(){
        fontSize = 12;
        isPrivate = false;
        messageColor = "black";
        fontFamily = "Arial";
    }
    
    /**
     * Returns the fonts size
     * @return font size as integer number
     */
    public int getFontSize() {
        return fontSize;
    }
    
    /**
     * Sets the fonts size. Must be between 12-25 pixels.
     * @param fontSize 
     */
    public void setFontSize(int fontSize) {
        if( fontSize >= MIN_FONT_SIZE && fontSize <= MAX_FONT_SIZE ){
            this.fontSize = fontSize;
        }
    }
    
    /**
     * Returns the font family set by user.
     * @return String containing fontFamily
     */
    public String getFontFamily() {
        return fontFamily;
    }
    
    /**
     * Sets the font family given by user.
     * @param fontFamily 
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
    
    /**
     * Returns boolean value if this is a private message or not
     * @return 
     */
    public boolean isIsPrivate() {
        return isPrivate;
    }
    
    /**
     * Sets the message to be private
     * @param isPrivate 
     */
    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    /**
     * Returns the name of the private message recipient.
     * @return String containing the private name 
     */
    public String getPrivateName() {
        return privateName;
    }
    
    /**
     * Sets the private message recipient name.
     * 
     * @param privateName containing the name 
     */
    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }
    
    /**
     * Returns the message color
     * @retun String containing message color
     */
    public String getMessageColor() {
        return messageColor;
    }
    
    /**
     * Sets the message color
     * 
     * @param messageColor 
     */
    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }
    
    /**
     * Returns the chat message
     * @retun String containing chat message
     */
    public String getChatMessage() {
        return chatMessage;
    }
    
    /**
     * Sets the chat message
     * 
     * @param chatMessage 
     */
    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
    
    
}
