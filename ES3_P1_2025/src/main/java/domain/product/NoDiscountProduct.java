package domain.product;

public class NoDiscountProduct extends Product {
    public NoDiscountProduct(String name, double standardPrice, String category) {
        super(name, standardPrice, category, new NoDiscountStrategy());
    }
}