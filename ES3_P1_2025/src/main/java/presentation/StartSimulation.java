package presentation;

import domain.order.Order;
import domain.payment.PaymentFactory;
import domain.payment.PaymentMethod;
import domain.product.*;

import domain.payment.Payment;

import domain.store.Store;

import java.util.LinkedList;
import java.util.List;

public class StartSimulation {

    public static void main (String [] args) {

        Store store = Store.getInstance(); //TODO: Create new store

        System.out.println("Welcome to " + store.getName() + " at " + store.getAddress());
        System.out.println("*******************");


        List<Product> productList = addProducts();
        show(productList);
        Order o = createOrder(productList);
        System.out.println("Pending order info:");
        System.out.println("--------");
        showOrder(o);
        System.out.println("-----------------------------------------");
        System.out.println("Order payment:");
        System.out.println("--------");
        //TODO: You can use these lines below to create different payment methods
        PaymentMethod paymentMethod = PaymentMethod.CreditCard;
        PaymentMethod paymentMethod2 = PaymentMethod.Bizum;
        PaymentMethod paymentMethod3 = PaymentMethod.PayPal;


        //TODO: Complete to create Payment
        //Mètode de pagament amb creditCard
        Payment pay = PaymentFactory.selectPaymentMethod(paymentMethod);

        o.confirmOrderAndPay(pay);
        System.out.println("Completed order info:");
        System.out.println("--------");
        showCompletedOrder(o);
        System.out.println("-----------------------------------------");
    }



    private static List<Product> addProducts() {
        List<Product> prodList = new LinkedList<>();

        /*TODO: Create 10 different products with different discount types and add them to prodList*/

        prodList.add(new NoDiscountProduct("Product1", 200.0, "Category1"));
        prodList.add(new FixedDiscountProduct("Product2", 200.0, "Category2", 20.0));
        prodList.add(new PercentageDiscountProduct("Product3", 300.0, "Category3", 10));
        prodList.add(new NoDiscountProduct("Product4", 400.0, "Category4"));
        prodList.add(new FixedDiscountProduct("Product5", 500.0, "Category5", 50.0));
        prodList.add(new PercentageDiscountProduct("Product6", 600.0, "Category6", 15));
        prodList.add(new NoDiscountProduct("Product7", 700.0, "Category7"));
        prodList.add(new FixedDiscountProduct("Product8", 800.0, "Category8", 80.0));
        prodList.add(new PercentageDiscountProduct("Product9", 900.0, "Category9", 20));
        prodList.add(new NoDiscountProduct("Product10", 1000.0, "Category10"));

        /*TODO: Change the discount type of one of those created products*/
        prodList.get(0).setDiscountStrategy(new PercentageDiscountStrategy(5));

        return prodList;
    }

    private static void show(List<Product> prodList){
        //System.out.println();
        System.out.println("Our store offers the following products:");
        for(Product p: prodList){
            show(p);
        }
        System.out.println("-----------------------------------------");

    }

    private static void show(Product p){
        System.out.println("Name:" + p.getName());
        System.out.println("Category:" + p.getCategory());
        System.out.println("Price:" + p.getStandardPrice());
        if (p.getStandardPrice() != p.getDiscountedPrice()) {
            System.out.println("Discounted Price: " + p.getDiscountedPrice());
        }
        System.out.println("******");
    }

    private static Order createOrder(List<Product> prodList) {
        /*TODO: Create a new pending order and add three different products from prodList*/
        Order o = new Order();
        o.addProduct(prodList.get(0));
        o.addProduct(prodList.get(1));
        o.addProduct(prodList.get(2));
        //TODO: Complete
        return o;
    }

    private static void showOrder(Order o) {
        System.out.println("Order ID: #" + o.getId());
        System.out.println("Total price w/discount: " + o.getTotal());
        System.out.println("Total price w/o discount: " + o.getTotalWithoutDiscount());
    }

    private static void showCompletedOrder(Order o) {
        showOrder(o);
        System.out.println("Start date and time: " + o.getPaymentStartDate());
        System.out.println("End date and time: " + o.getPaymentEndDate());
        System.out.println("Congratulations for buying the following products: ");
        System.out.println(o.getProductNames());
    }
}
