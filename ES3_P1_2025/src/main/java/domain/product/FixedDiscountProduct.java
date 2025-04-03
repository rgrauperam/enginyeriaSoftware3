package domain.product;

class FixedDiscountProduct extends Product {
    private double discountValue;

    public FixedDiscountProduct(String name, double standardPrice, String category, double discountValue) {
        super(name, standardPrice, category);
        this.discountValue = discountValue;
    }

    @Override
    public double getFinalPrice() {
        return standardPrice - discountValue;
    }
}
