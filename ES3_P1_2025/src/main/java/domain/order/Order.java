package domain.order;

public class Order {

    private long orderId;
    private Product productList;

    public Order(long orderId, Product productList) {
        this.orderId = orderId;
        this.productList = productList;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Product getProductList() {
        return productList;
    }

    public void setProductList(Product productList) {
        this.productList = productList;
    }
}
