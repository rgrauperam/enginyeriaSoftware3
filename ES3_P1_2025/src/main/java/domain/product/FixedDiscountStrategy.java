package domain.product;

public class FixedDiscountStrategy implements DiscountStrategy {
    private double discountValue;

    public FixedDiscountStrategy(double discountValue) {
        this.discountValue = discountValue;
    }

    @Override
    public double applyDiscount(double standardPrice) {
        return Math.max(0, standardPrice - discountValue);
    }
}