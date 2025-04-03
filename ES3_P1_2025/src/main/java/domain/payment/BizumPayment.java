package domain.payment;

import domain.order.ConfirmedOrder;

import java.util.Date;

public class BizumPayment extends Payment{
    public BizumPayment(Date start, Date end, ConfirmedOrder order) {
        super(start, end, order);
    }
}
