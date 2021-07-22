package main.java.com.sowatec.pg.stack.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.java.com.sowatec.pg.stack.Util;
import main.java.com.sowatec.pg.stack.data.DatabaseExecutor;
import main.java.com.sowatec.pg.stack.data.Model;
import main.java.com.sowatec.pg.stack.data.dbo.UserDBO;
import main.java.com.sowatec.pg.stack.pane.*;

import java.net.URL;
import java.util.ResourceBundle;

public class StackClientMainController implements Initializable {

    public BorderPane parent;
    private Model model;
    private static StackClientMainController controller;

    public StackClientMainController() {
        this.model = new Model();
        controller = this;
    }

    public static StackClientMainController getController() {
        return controller;
    }

    public void showAskPane(ActionEvent actionEvent) {
        if (model.isLoggedIn())
            parent.setCenter(new AskPane());
        else
            showLoginPane(actionEvent);
    }

    public void showAccountPane(ActionEvent actionEvent) {
        if (model.isLoggedIn())
            parent.setCenter(new AccountPane());
        else
            showLoginPane(actionEvent);
    }

    public void showInfoPane(ActionEvent actionEvent) {
        if (model.isLoggedIn()) parent.setCenter(new InfoPane());
        else
            showLoginPane(actionEvent);
    }

    public void showSettingsPane(ActionEvent actionEvent) {
        if (model.isLoggedIn()) parent.setCenter(new SettingsPane());
        else
            showLoginPane(actionEvent);
    }

    public void showLoginPane(ActionEvent actionEvent) {
        parent.setCenter(new LoginPane());
    }

    public void showRegisterPane(ActionEvent actionEvent) {
        parent.setCenter(new RegisterPane());
    }

    public void showMainPane(ActionEvent actionEvent) {
        if (model.isLoggedIn()) parent.setCenter(new MainPane());
        else
            showLoginPane(actionEvent);
    }

    public void loginAs(UserDBO userDBO) {
        model.loginAs(userDBO);
        showMainPane(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showLoginPane(null);
        loginAs(DatabaseExecutor.getUser(new UserDBO("admin", Util.hash("admin"))));
        showAskPane(null);
    }
}
