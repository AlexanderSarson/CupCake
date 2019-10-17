package logic;

public abstract class BaseEntity {

    protected long id;

    public BaseEntity(){}
    public void setID(long id) {this.id = id;}
    public long getID() {return id;}

}
