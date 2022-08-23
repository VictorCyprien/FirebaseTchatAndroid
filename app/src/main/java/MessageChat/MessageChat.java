package MessageChat;

public class MessageChat {
    private String message;
    private String username;
    private String email;
    private String date;

    public void ChatMessage(String message, String username, String email, String date){
        this.message = message;
        this.username = username;
        this.email = email;
        this.date = date;
    }

    public void ChatMessage(){

    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
