package logic;

/**
 * The base entity class is the base of all entities in the system
 * @author Oscar
 * version 1.0
 */
public abstract class BaseEntity {

    protected int id;

    public BaseEntity(){}

    /**
     * Gets the ID of the base entity.
     * @return integer ID of the entity.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the base entity.
     * @param id The new id of the base entity.
     */
    public void setId(int id) {
        this.id = id;
    }
}
