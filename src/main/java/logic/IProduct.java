package logic;

/**
 * Basic interface for all the common product methods.
 * @author Benjamin Paepke
 * @version 1.0
 */
public interface IProduct {
    String getName();
    void setName(String name);
    int getPrice();
    void setPrice(int price);
    int getId();
    void setId(int id);
}
