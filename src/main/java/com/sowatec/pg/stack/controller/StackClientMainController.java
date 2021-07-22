package main.java.com.sowatec.pg.stack.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import main.java.com.sowatec.pg.stack.data.Model;
import main.java.com.sowatec.pg.stack.pane.*;

public class StackClientMainController {

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

    public void showAccountPane(ActionEvent actionEvent) {
        if (model.isLoggedIn())
            parent.setCenter(new AccountPane());
        else
            showRegisterPane(actionEvent);
    }

    public void showInfoPane(ActionEvent actionEvent) {
        if (model.isLoggedIn()) parent.setCenter(new InfoPane());
        else
            showRegisterPane(actionEvent);
    }

    public void showSettingsPane(ActionEvent actionEvent) {
        if (model.isLoggedIn()) parent.setCenter(new SettingsPane());
        else
            showRegisterPane(actionEvent);
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
            showRegisterPane(actionEvent);
    }
}
