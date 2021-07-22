package main.java.com.sowatec.pg.stack.data;


import main.java.com.sowatec.pg.stack.data.dbo.UserDBO;

public class Model {
    private UserDBO userDBO;

    public boolean isLoggedIn() {
        return userDBO != null;
    }

    public void loginAs(UserDBO userDBO) {
        this.userDBO = userDBO;
    }
}
