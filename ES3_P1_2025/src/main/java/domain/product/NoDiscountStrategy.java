package domain.product;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double standardPrice) {
        return standardPrice;
    }
}