package Dictionary;

public class User {
   private String username;
   private int password;

    public void setPass(String p){
        password = Integer.parseInt(p);
    }
    public void setUsername(String u){
        username = u;
    }
    public boolean login(String u, String p){
        if(username.equals(u) && password==Integer.parseInt(p))
            return true;
        else
            return false;
    }
}
