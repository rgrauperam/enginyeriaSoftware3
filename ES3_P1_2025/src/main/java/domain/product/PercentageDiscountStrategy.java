package domain.product;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private int discountPercentage;

    public PercentageDiscountStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(double standardPrice) {
        return standardPrice - (standardPrice * discountPercentage / 100.0);
    }
}