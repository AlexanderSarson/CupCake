package logic;

import persistence.OrderException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents an order in the system, an order is comprised of LineItems {@link logic.LineItem}
 * @author Oscar
 * @author Benjamin Paepke
 * @version 1.0
 */
public class Order extends BaseEntity {
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    private LocalDate date;
    public Order(LocalDate date) {
        this.date = date;
    }

    /**
     * Adds a LineItem to the order.
     * @param item the item to be added.
     */
    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    /**
     * Removes a LineItem from the order, using an index.
     * @param index The index at which to remove a LineItem.
     * @throws OrderException If the index is out of bounds.
     */
    public void removeLineItem(int index) throws OrderException {
        if(!validateIndex(index))
            throw new OrderException("Index out of bounds");
        else
            lineItems.remove(index);
    }

    /**
     * Removes a LineItem from the order, if the item is not contained in the order nothing happens.
     * @param item The item to be removed.
     */
    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
    }

    /**
     * Gets a LineItem from the order, without removing it.
     * @param index The index at which to get the item.
     * @return LineItem at the given index.
     */
    public LineItem getLineItem(int index) throws OrderException {
        if(!validateIndex(index))
            throw new OrderException("Index out of bounds");
        else
            return lineItems.get(index);
    }

    /**
     * Gets the Total quantity of product in the order.
     * @return The total number of product in the order.
     */
    public int getTotalQuantity() {
        int res = 0;
        for (LineItem item: lineItems) {
            res += item.getQuantity();
        }
        return res;
    }

    /**
     * Returns the number of LineItems in the order.
     * @return The number of LineItems in the order.
     */
    public int getSize() {
        return lineItems.size();
    }

    /**
     * Validates the index, check if the index is within the bounds.
     * @param index The index to be checked.
     * @return TRUE if the index is within the bounds, FALSE if the index is outside the bounds.
     */
    private boolean validateIndex(int index) {
        return (index < lineItems.size() && index >= 0);
    }

}
