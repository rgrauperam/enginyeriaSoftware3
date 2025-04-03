package domain.payment;

import domain.order.ConfirmedOrder;

import java.util.Date;

public class CreditCardPayment extends Payment{
    public CreditCardPayment(Date start, Date end, ConfirmedOrder order) {
        super(start, end, order);
    }
}
