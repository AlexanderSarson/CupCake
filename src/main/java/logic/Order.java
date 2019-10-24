package logic;

import java.util.ArrayList;

/**
 * @author Oscar
 * @author Benjamin Paepke
 * @version 1.0
 */
public class Order extends BaseEntity {
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    public Order() {}

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public void removeLineItem(int index) {
        lineItems.remove(index);
    }
    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
    }

    public LineItem getLineItem(int index) {
        return lineItems.get(index);
    }

    public int getTotalQuantity() {
        int res = 0;
        for (LineItem item: lineItems) {
            res += item.getQuantity();
        }
        return res;
    }

    public int getSize() {
        return lineItems.size();
    }
}
