package logic;

/**
 * Basic interface for all the common product methods.
 * @author Benjamin Paepke
 * @version 1.0
 */
public interface IProduct {
    /**
     * Gets the name of the Product.
     * @return a String representing the name of the product.
     */
    String getName();

    /**
     * Sets the name of the product.
     * @param name the new name of the product.
     */
    void setName(String name);

    /**
     * Gets the price of the products.
     * @return integer value, representing the price of the product.
     */
    int getPrice();

    /**
     * Sets the price of the product.
     * @param price the new price of the product.
     */
    void setPrice(int price);

    /**
     * Gets the id of the product.
     * @return integer value, representing the id of the product.
     */
    int getId();

    /**
     * Sets the id of the product.
     * @param id the new id of the product.
     */
    void setId(int id);
}
