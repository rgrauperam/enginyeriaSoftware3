package domain.order;

import domain.payment.Payment;

public interface OrderState {
    void confirmOrderAndPay(Order order, Payment payment);
    double calculateTotalProducts(Order order);
}