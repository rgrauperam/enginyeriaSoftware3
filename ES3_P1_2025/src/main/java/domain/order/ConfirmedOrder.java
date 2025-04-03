package domain.order;

import java.util.Date;

public class ConfirmedOrder extends Order {
    private double totalOrder;
    private Date confirmationDate;

    public ConfirmedOrder(long orderId, Product productList) {
        super(orderId, productList);
    }
}
