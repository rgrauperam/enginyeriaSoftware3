package domain.payment;

public class BizumPayment extends Payment {
    @Override
    protected void obtainPaymentData() {
        System.out.println("Customer information received");
    }

    @Override
    protected void validateData() {
        System.out.println("Validated customer data successfully");
    }

    @Override
    protected boolean pay() {
        System.out.println("Bizum payment received");
        return true;
    }

    @Override
    protected void sendReceipt() {
        System.out.println("Receipt sent to customer");
    }
}