package MessageChat;

public class MessageChat {
    private String message;
    private String username;
    private String email;
    private String date;
    private String image_attachement;

    //Used by firebase to get data
    public MessageChat(){

    }

    public MessageChat(String message, String username, String email, String date, String image_attachement) {
        this.message = message;
        this.username = username;
        this.email = email;
        this.date = date;
        this.image_attachement = null;

        if (image_attachement != null){
            this.image_attachement = image_attachement;
        }
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

    public String getImage_attachement() {
        return image_attachement;
    }

    public void setImage_attachement(String image_attachement) {
        this.image_attachement = image_attachement;
    }
}
