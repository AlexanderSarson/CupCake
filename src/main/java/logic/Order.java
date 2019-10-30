package logic;

import persistence.OrderException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the system, an order is comprised of LineItems {@link logic.LineItem}
 * @author Oscar
 * @author Benjamin Paepke
 * @version 1.0
 */
public class Order extends BaseEntity {
    private List<LineItem> lineItems = new ArrayList<>();
    private LocalDate date;

    public Order() {};
    public Order(List<LineItem> lineItems, LocalDate date) {
        this.lineItems = lineItems;
        this.date = date;
    }
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
     * Adds a cupcake to the order.
     * @param cupcake The cupcake to be added.
     */
    public void addCupcakeToOrder(Cupcake cupcake) {
        int index = getCupcakeIndex(cupcake);
        if(index >= 0) {
            lineItems.get(index).incrementQuantity();
        } else {
            lineItems.add(new LineItem(cupcake,1));
        }
    }

    /**
     * Gets the index of the cupcake, if it exists in the order.
     * @param cupcake The cupcake to be found
     * @return 0 or positive value if the cupcake is present in the order, -1 if the cupcake is not present in the order.
     */
    private int getCupcakeIndex(Cupcake cupcake) {
        for (int i = 0; i < lineItems.size(); i++) {
            Cupcake c = lineItems.get(i).getCupcake();
            boolean botSame = c.getBottom().getId() == cupcake.getBottom().getId();
            boolean topSame = c.getTopping().getId() == cupcake.getTopping().getId();
            if( botSame && topSame) {
                return i;
            }
        }
        return -1;
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
     * Returns the list of lineItems in the order.
     * @return List of all LineItems in the order.
     */
    public List<LineItem> getLineItems() {
        return lineItems;
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
     * Gets the total order price
     * @return The total price of the order.
     */
    public int getOrderPrice() {
        int total = 0;
        for (LineItem item: lineItems) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Returns the number of LineItems in the order.
     * @return The number of LineItems in the order.
     */
    public int getSize() {
        return lineItems.size();
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    /**
     * Validates the index, check if the index is within the bounds.
     * @param index The index to be checked.
     * @return TRUE if the index is within the bounds, FALSE if the index is outside the bounds.
     */
    private boolean validateIndex(int index) {
        return (index < lineItems.size() && index >= 0);
    }
}
