package domain.payment;

import domain.order.ConfirmedOrder;

import java.util.Date;

public class PayPalPayment extends Payment{
    public PayPalPayment(Date start, Date end, ConfirmedOrder order) {
        super(start, end, order);
    }
}
