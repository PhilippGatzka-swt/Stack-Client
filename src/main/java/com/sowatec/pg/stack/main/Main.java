package main.java.com.sowatec.pg.stack.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL loc = new File("src/main/resources/fxml/stack-client-main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(loc);
        primaryStage.setTitle("Stack-Client");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
