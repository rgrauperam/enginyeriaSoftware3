package domain.order;

import domain.product.Product;
import domain.payment.Payment;
import domain.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private List<Product> productList;
    private OrderState state;

    public Order() {
        this.id = Store.getInstance().nextAvailableOrderNumber();
        this.productList = new ArrayList<>();
        this.state = new PendingOrder();
    }

    public long getId() {
        return id;
    }

    public double getTotal() {
        return state.calculateTotalProducts(this);
    }

    public double getTotalWithoutDiscount() {
        return productList.stream().mapToDouble(Product::getStandardPrice).sum();
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (Product product : productList) {
            names.add(product.getName());
        }
        return names;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void deleteProduct(Product product) {
        productList.remove(product);
    }

    public void confirmOrderAndPay(Payment payment) {
        state.confirmOrderAndPay(this, payment);
    }

    public String getPaymentStartDate() {
        return null;
    }

    public String getPaymentEndDate() {
        return null;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double calculateTotalProducts() {
        return productList.stream().mapToDouble(Product::getDiscountedPrice).sum();
    }
}