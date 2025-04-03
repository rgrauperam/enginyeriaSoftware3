package domain.product;

public class PercentageDiscountProduct extends Product {
    public PercentageDiscountProduct(String name, double standardPrice, String category, int discountPercentage) {
        super(name, standardPrice, category, new PercentageDiscountStrategy(discountPercentage));
    }


}