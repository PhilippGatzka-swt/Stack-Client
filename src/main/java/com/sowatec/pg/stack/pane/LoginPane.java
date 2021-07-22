package main.java.com.sowatec.pg.stack.pane;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import main.java.com.sowatec.pg.stack.Util;
import main.java.com.sowatec.pg.stack.controller.StackClientMainController;
import main.java.com.sowatec.pg.stack.data.dbo.UserDBO;

public class LoginPane extends AbstractPane<Node, Node, VBox, Node, Node> {

    TextField username;
    PasswordField password;
    Label warn;

    public LoginPane() {
        super();
    }

    @Override
    protected void init() {
        center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(5);

        javafx.geometry.Insets labelInsets = new Insets(5, 0, 0, 0);
        int def_w = 300;

        warn = new Label("");
        warn.setTextFill(Paint.valueOf("#FF0000"));

        Label l_username = new Label("Username");
        VBox.setMargin(l_username, labelInsets);
        username = new TextField();
        username.setMaxWidth(def_w);

        Label l_password = new Label("Password");
        VBox.setMargin(l_password, labelInsets);
        password = new PasswordField();
        password.setMaxWidth(def_w);

        Button login = new Button("Login");
        login.setMaxWidth(def_w);
        login.setOnAction(this);
        login.setId("login");

        Button register = new Button("Create new account");
        register.setMaxWidth(def_w);
        register.setOnAction(this);
        register.setId("register");

        center.getChildren().addAll(l_username, username, l_password, password, warn, login, register);
        setCenter(center);
    }

    @Override
    public void handle(ActionEvent e) {
        String id = ((Button) e.getSource()).getId();
        switch (id) {
            case "register":
                StackClientMainController.getController().showRegisterPane(null);
                break;
            case "login":
                String s_username = username.getText();
                String s_password = Util.hash(username.getText());

                UserDBO userDBO = new UserDBO(s_username, s_password);
                boolean userExists = UserDBO.doesUserExist(userDBO);
                if (userExists) {
                    StackClientMainController.getController().loginAs(UserDBO.getUser(userDBO));
                } else {
                    warn.setText("Login not found, please check your input");
                    warn.setVisible(true);
                }
                break;
        }


    }
}
