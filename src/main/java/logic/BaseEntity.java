package logic;

/**
 * @author Oscar
 * version 1.0
 */

public abstract class BaseEntity {

    protected long id;


    public BaseEntity(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
