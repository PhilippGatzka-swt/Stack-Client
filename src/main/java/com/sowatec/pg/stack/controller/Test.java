package main.java.com.sowatec.pg.stack.controller;

import main.java.com.sowatec.pg.stack.Util;
import main.java.com.sowatec.pg.stack.data.DatabaseExecutor;
import main.java.com.sowatec.pg.stack.data.dbo.UserDBO;

public class Test {

    public static void main(String[] args){
        DatabaseExecutor.registerUser(new UserDBO("Philipp","gatzka@sowatec.com", Util.hash("peter.griffin")));

    }

}
