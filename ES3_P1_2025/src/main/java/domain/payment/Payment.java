package domain.payment;

import domain.order.ConfirmedOrder;

import java.util.Date;

public class Payment {
    private Date start;
    private Date end;
    private ConfirmedOrder order;

    public Payment(Date start, Date end, ConfirmedOrder order) {
        this.start = start;
        this.end = end;
        this.order = order;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ConfirmedOrder getOrder() {
        return order;
    }

    public void setOrder(ConfirmedOrder order) {
        this.order = order;
    }
}
