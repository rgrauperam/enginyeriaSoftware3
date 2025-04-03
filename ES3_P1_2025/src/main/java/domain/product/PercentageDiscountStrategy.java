package domain.product;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private int discountPercentage;

    public PercentageDiscountStrategy(int discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(double standardPrice) {
        return standardPrice * (1 - discountPercentage / 100.0);
    }
}