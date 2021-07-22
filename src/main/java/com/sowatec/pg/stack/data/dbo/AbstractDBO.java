package main.java.com.sowatec.pg.stack.data.dbo;

public abstract class AbstractDBO {
    protected int id;
    protected Action action;
    protected String type;

    public AbstractDBO(int id) {
        type = "AbstractDBO";
    }

    public void setAction(Action action){
        this.action = action;
    }

    public AbstractDBO() {
        type = "AbstractDBO";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Action {
        INSERT
    }
}
