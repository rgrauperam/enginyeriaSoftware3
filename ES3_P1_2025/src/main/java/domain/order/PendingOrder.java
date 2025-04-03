package domain.order;

import domain.payment.Payment;

public class PendingOrder implements OrderState {
    @Override
    public void confirmOrderAndPay(Order order, Payment payment) {
        order.setState(new ConfirmedOrder(order, payment));
    }

    @Override
    public double calculateTotalProducts(Order order) {
        return order.getProductList().stream().mapToDouble(product -> product.getDiscountedPrice()).sum();
    }
}