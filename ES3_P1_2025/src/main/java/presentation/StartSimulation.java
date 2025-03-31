package presentation;

import domain.order.Order;
import domain.order.Product;

import domain.payment.Payment;

import domain.store.Store;

import java.util.LinkedList;
import java.util.List;

public class StartSimulation {

    public static void main (String [] args) {

        Store store = null; //TODO: Create new store

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
        //PaymentMethod paymentMethod = PaymentMethod.CreditCard;
        //PaymentMethod paymentMethod = PaymentMethod.Bizum;
        //PaymentMethod paymentMethod = PaymentMethod.PayPal;


        //TODO: Complete to create Payment
        Payment pay = null;

        o.confirmOrderAndPay(pay);
        System.out.println("Completed order info:");
        System.out.println("--------");
        showCompletedOrder(o);
        System.out.println("-----------------------------------------");
    }



    private static List<Product> addProducts() {
        List<Product> prodList = new LinkedList<>();

        /*TODO: Create 10 different products with different discount types and add them to prodList*/

        /*TODO: Change the discount type of one of those created products*/

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
        Order o = null;
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
