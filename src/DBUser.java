public class DBUser {
    private String UserName;
    private String Password;

    public DBUser(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }
    
    public void setUserName(String userName){
        this.UserName = userName;
    }
    
    public String getPassword() {
        return Password;
    } 
    
    public void setPassword(String passWord){
        this.Password = passWord;
    }
}