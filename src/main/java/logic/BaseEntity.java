package logic;

/**
 * @author Oscar
 * version 1.0
 */

public abstract class BaseEntity {

    protected int id;


    public BaseEntity(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
