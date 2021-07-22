package main.java.com.sowatec.pg.stack.data;

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
            String success = new DataInputStream(socket.getInputStream()).readUTF().replace("success: ","");
            return Boolean.parseBoolean(success);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
