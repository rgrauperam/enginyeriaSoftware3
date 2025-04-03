package domain.order;

public class PendingOrder extends Order{
    public PendingOrder(long orderId, Product productList) {
        super(orderId, productList);
    }
}
