package domain.product;

class NoDiscountProduct extends Product {
    public NoDiscountProduct(String name, double standardPrice, String category) {
        super(name, standardPrice, category);
    }

    @Override
    public double getFinalPrice() {
        return standardPrice;
    }
}
