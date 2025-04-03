package domain.product;

public class FixedDiscountProduct extends Product {
    public FixedDiscountProduct(String name, double standardPrice, String category, double discountValue) {
        super(name, standardPrice, category, new FixedDiscountStrategy(discountValue));
    }
}