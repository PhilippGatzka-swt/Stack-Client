package main.java.com.sowatec.pg.stack.data;

import main.java.com.sowatec.pg.stack.DatabaseConnector;
import main.java.com.sowatec.pg.stack.Util;
import main.java.com.sowatec.pg.stack.data.dbo.AbstractDBO;
import main.java.com.sowatec.pg.stack.data.dbo.UserDBO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

public class DatabaseExecutor {
    private static DatabaseExecutor instance;


    public DatabaseExecutor() {

    }

    public static DatabaseExecutor get() {
        if (instance == null) instance = new DatabaseExecutor();
        return instance;
    }

    public synchronized static boolean registerUser(UserDBO userDBO) {
        try {
            Socket socket = new Socket("localhost", 6666);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            JAXBContext jaxbContext = JAXBContext.newInstance(UserDBO.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            userDBO.setAction(AbstractDBO.Action.INSERT);
            jaxbMarshaller.marshal(userDBO, writer);
            String xmlContent = writer.toString();
            dos.writeUTF(xmlContent);
            String success = new DataInputStream(socket.getInputStream()).readUTF().replace("success: ", "");
            return Boolean.parseBoolean(success);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean doesUserExist(UserDBO userDBO) {
        try {
            PreparedStatement select = DatabaseConnector.getConnection().prepareStatement("select * from t_user where username = ? and password = ?");
            select.setString(1,userDBO.getUsername());
            select.setString(2,userDBO.getPassword());
            ResultSet set = select.executeQuery();
            return set.next();
        } catch (Exception e) {
            Util.log(Level.WARNING,"DatabaseExecutor.doesUserExist(); " +   e.toString());
            return false;
        }
    }

    public static UserDBO getUser(UserDBO userDBO) {
        try {
            PreparedStatement select = DatabaseConnector.getConnection().prepareStatement("select * from t_user where username = ? and password = ?");
            select.setString(1,userDBO.getUsername());
            select.setString(2,userDBO.getPassword());
            ResultSet set = select.executeQuery();
            set.next();
            UserDBO login = new UserDBO();
            login.setUsername(set.getString("username"));
            login.setId(set.getInt("id"));
            login.setEmail(set.getString("email"));
            return login;
        } catch (Exception e) {
            Util.log(Level.WARNING,"DatabaseExecutor.getUser(); " +  e);
            return null;
        }
    }
}
