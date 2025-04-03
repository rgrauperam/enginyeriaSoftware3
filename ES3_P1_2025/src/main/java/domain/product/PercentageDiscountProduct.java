package domain.product;

class PercentageDiscountProduct extends Product {
    private int discountPercentage;

    public PercentageDiscountProduct(String name, double standardPrice, String category, int discountPercentage) {
        super(name, standardPrice, category);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getFinalPrice() {
        return standardPrice - (standardPrice * discountPercentage / 100.0);
    }
}


