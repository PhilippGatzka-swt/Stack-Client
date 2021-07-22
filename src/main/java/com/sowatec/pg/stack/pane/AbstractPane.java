package main.java.com.sowatec.pg.stack.pane;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public abstract class AbstractPane<T extends Node, L extends Node, C extends Node, R extends Node, B extends Node> extends BorderPane {

    protected T top;
    protected L left;
    protected C center;
    protected R right;
    protected B bottom;

    protected static final int pref_w = 1130;
    protected static final int pref_h = 830;

    public AbstractPane() {
        setPrefSize(pref_w, pref_h);
        setMaxSize(pref_w, pref_h);
        setMinSize(pref_w, pref_h);
        setStyle("-fx-background-color: #CFCFCF");
        init();
    }

    protected abstract void init();
}
