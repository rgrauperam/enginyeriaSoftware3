package domain.payment;

import java.util.Date;

public abstract class Payment {
    private Date start;
    private Date end;

    public Payment() {
        this.start = new Date();
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public final void processPayment() {
        obtainPaymentData();
        validateData();
        if (pay()) {
            setEndDate();
            sendReceipt();
        }
    }

    protected abstract void obtainPaymentData();
    protected abstract void validateData();
    protected abstract boolean pay();
    protected void setEndDate() {
        this.end = new Date();
    }
    protected abstract void sendReceipt();
}