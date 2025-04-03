package domain.order;

import domain.payment.Payment;

import java.util.Date;

public class ConfirmedOrder implements OrderState {
    private double totalOrder;
    private Date confirmationDate;
    private Payment payment;

    public ConfirmedOrder(Order order, Payment payment) {
        this.totalOrder = order.calculateTotalProducts();
        this.confirmationDate = new Date();
        this.payment = payment;
        this.payment.processPayment();
    }

    @Override
    public void confirmOrderAndPay(Order order, Payment payment) {
        // Already confirmed
    }

    @Override
    public double calculateTotalProducts(Order order) {
        return totalOrder;
    }

    public double getTotal() {
        return totalOrder;
    }

    public Date getDate() {
        return confirmationDate;
    }

    public String getPaymentStartDate() {
        return payment.getStart().toString();
    }

    public String getPaymentEndDate() {
        return payment.getEnd().toString();
    }
}