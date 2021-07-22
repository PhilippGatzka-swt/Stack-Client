package main.java.com.sowatec.pg.stack.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.util.Objects;

public class RegisterPane extends AbstractPane<Node, Node, VBox, Node, Node> implements EventHandler<ActionEvent> {

    private TextField username;
    private TextField email;
    private PasswordField password;
    private PasswordField repeatPassword;
    private Label warn;

    public RegisterPane() {
        super();
    }

    @Override
    protected void init() {
        center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(5);

        Insets labelInsets = new Insets(5, 0, 0, 0);
        int def_w = 300;

        warn = new Label("");
        warn.setTextFill(Paint.valueOf("#FF0000"));

        Label l_username = new Label("Username");
        VBox.setMargin(l_username, labelInsets);
        username = new TextField();
        username.setMaxWidth(def_w);

        Label l_email = new Label("Email");
        VBox.setMargin(l_email, labelInsets);
        email = new TextField();
        email.setMaxWidth(def_w);

        Label l_password = new Label("Password");
        VBox.setMargin(l_password, labelInsets);
        password = new PasswordField();
        password.setMaxWidth(def_w);

        Label l_repeat_password = new Label("Repeat password");
        VBox.setMargin(l_repeat_password, labelInsets);
        repeatPassword = new PasswordField();
        repeatPassword.setMaxWidth(def_w);

        Button register = new Button("Register");
        register.setMaxWidth(def_w);
        register.setOnAction(this);

        center.getChildren().addAll(l_username, username, l_email, email, l_password, password, l_repeat_password, repeatPassword, warn, register);
        setCenter(center);
    }

    @Override
    public synchronized void handle(ActionEvent event) {
        String s_username = username.getText();
        String s_email = email.getText();
        String s_password;
        if (Objects.equals(Util.hash(password.getText()), Util.hash(repeatPassword.getText()))) {
            s_password = Util.hash(password.getText());
        } else {
            warn.setVisible(true);
            warn.setText("Passwords do not match!");
            return;
        }
        UserDBO userDBO = new UserDBO(s_username, s_email, s_password);
        boolean success = UserDBO.register(userDBO);

        if (!success) {
            warn.setVisible(true);
            warn.setText("Username or email already registered");
        } else {
            StackClientMainController.getController().showLoginPane(null);
        }

    }
}
