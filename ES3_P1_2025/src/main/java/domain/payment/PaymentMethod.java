package domain.payment;

/*
public enum PaymentMethod {
    PayPal,CreditCard,Bizum
}
*/
public enum PaymentMethod {
    CreditCard {
        @Override
        public Payment createPayment() {
            return new CreditCardPayment();
        }
    },
    Bizum {
        @Override
        public Payment createPayment() {
            return new BizumPayment();
        }
    },
    PayPal {
        @Override
        public Payment createPayment() {
            return new PayPalPayment();
        }
    };

    public abstract Payment createPayment();
}