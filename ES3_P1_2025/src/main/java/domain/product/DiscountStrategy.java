package domain.product;

public interface DiscountStrategy {
    double applyDiscount(double standardPrice);
}