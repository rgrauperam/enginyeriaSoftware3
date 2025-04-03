package domain.payment;

public class PaymentFactory {
    public static Payment selectPaymentMethod(PaymentMethod method) {
        switch (method) {
            case Bizum:
                return new BizumPayment();
            case PayPal:
                return new PayPalPayment();
            case CreditCard:
                return new CreditCardPayment();
            default:
                throw new IllegalArgumentException("Unknown payment method");
        }
    }
}