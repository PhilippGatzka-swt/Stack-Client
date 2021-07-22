package main.java.com.sowatec.pg.stack.data.dbo;

import main.java.com.sowatec.pg.stack.data.DatabaseExecutor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDBO extends AbstractDBO {
    private String username;
    private String email;
    private String password;

    public UserDBO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = "UserDBO";
    }

    public UserDBO(int id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = "UserDBO";
    }

    public UserDBO() {
        this.type = "UserDBO";
    }

    public UserDBO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean register(UserDBO userDBO) {
        return DatabaseExecutor.registerUser(userDBO);
    }

    public static boolean doesUserExist(UserDBO userDBO) {
        return DatabaseExecutor.doesUserExist(userDBO);
    }

    public static UserDBO getUser(UserDBO userDBO) {
        return DatabaseExecutor.getUser(userDBO);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
