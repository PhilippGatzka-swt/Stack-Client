package main.java.com.sowatec.pg.stack.pane;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.fxmisc.richtext.CodeArea;


public class AskPane extends AbstractPane<Label, Node, ScrollPane, Node, HBox> {
    private VBox editor;

    public AskPane() {

    }

    @Override
    protected void init() {
        top = new Label("Ask a question");
        top.setFont(new Font("System Bold", 28));
        top.setPadding(new Insets(10));
        setTop(top);

        center = new ScrollPane();
        editor = new VBox();
        center.setContent(editor);

        Label l_title = new Label("Title");
        l_title.setFont(new Font("System", 15));
        TextField title = new TextField();
        Label l_content = new Label("Explain your question over here");
        l_content.setFont(new Font("System", 15));
        TextArea content = new TextArea();
        content.setPrefHeight(100);
        editor.setSpacing(10);
        editor.setPadding(new Insets(5, 5, 5, 5));
        editor.getChildren().addAll(l_title, title, new Separator(Orientation.HORIZONTAL), l_content, content, new Separator(Orientation.HORIZONTAL));
        editor.heightProperty().addListener((observable, oldValue, newValue) -> center.setVvalue((Double) newValue));
        setCenter(center);
        BorderPane.setMargin(center, new Insets(50, 150, 50, 150));

        editor.setAlignment(Pos.TOP_CENTER);

        bottom = new HBox();
        bottom.setPadding(new Insets(10));
        Button addText = new Button("Add text field");
        Button addCode = new Button("Add code field");
        addText.setOnAction(e -> {
            TextArea textArea = new TextArea();
            textArea.setPrefHeight(100);
            Button remove = new Button("Remove");
            Separator separator = new Separator(Orientation.HORIZONTAL);
            remove.setOnAction(ee -> editor.getChildren().removeAll(remove, textArea, separator));
            editor.getChildren().addAll(textArea, remove, separator);
        });
        addCode.setOnAction(e -> {
            CodeArea codeArea = new CodeArea();
            Button remove = new Button("Remove");
            Separator separator = new Separator(Orientation.HORIZONTAL);
            remove.setOnAction(ee -> editor.getChildren().removeAll(remove, codeArea, separator));
            codeArea.setWrapText(true);
            editor.getChildren().addAll(codeArea, remove, separator);
        });



        bottom.getChildren().addAll(addText, addCode);
        bottom.setAlignment(Pos.CENTER);
        setBottom(bottom);
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
