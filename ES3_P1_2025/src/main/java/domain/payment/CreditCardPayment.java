package domain.payment;

public class CreditCardPayment extends Payment {
    @Override
    protected void obtainPaymentData() {
        System.out.println("Credit card information received");
    }

    @Override
    protected void validateData() {
        System.out.println("Validated customer data successfully");
    }

    @Override
    protected boolean pay() {
        System.out.println("Credit card payment made successfully");
        return true;
    }

    @Override
    protected void sendReceipt() {
        System.out.println("Receipt sent to customer");
    }
}