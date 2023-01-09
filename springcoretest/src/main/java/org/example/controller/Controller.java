package org.example.controller;

import java.util.ArrayList;
import java.util.Scanner;
import org.example.model.Cart;
import org.example.model.Product;
import org.example.model.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/*
 * @author Oksiuta Andrii
 * 09.01.2023
 * 12:30
 */

public class Controller {

  private Controller() {
    throw new IllegalStateException("Controller class");
  }

  public static void runConsoleApp() {
    System.out.println("App started");

    String configPath = "./springcoretest/src/main/resources/ApplicationContext.xml";
    ApplicationContext context = new FileSystemXmlApplicationContext(configPath);

    ProductRepository productRepository = context.getBean("productRepository",
        ProductRepository.class);

    System.out.println("The contents of the repository:");

    for (Product product : productRepository.getAllProducts()) {
      System.out.println("ID: " + product.getId() + "\tName: " + product.getName() + "\tPrice: "
          + product.getPrice());
    }
    System.out.println("You may start work with repository and cartList...");

    cartSwitcher(context);
  }

  private static void cartSwitcher(ApplicationContext context) {
    if (context != null) {
      Cart cart = getNewCart(context);
      while (true) {
        String command = scanner("""
            Enter command of action:
            0 - create new Cart
            1 - remove Product from Cart
            2 - add Product to Cart
            9 - finish work with Cart
            -->\s""");
        switch (Integer.parseInt(command)) {
          case 0 -> {
            cart = getNewCart(context);
            cart.info();
          }
          case 1 -> {
            cartRemove(cart);
            cart.info();
          }
          case 2 -> {
            cartAdd(cart);
            cart.info();
          }
          case 9 -> {
            cart.info();
            return;
          }
          default -> System.out.println("wrong command!!!");
        }
      }
    }
  }
  private static Cart getNewCart(ApplicationContext context) {
    if (context == null) {
      return new Cart();
    }
    Cart resultCart = context.getBean("cart", Cart.class);
    resultCart.setCartList(new ArrayList<>());
    return resultCart;
  }

  private static void cartAdd(Cart cart) {
    if (cart != null) {
      String addId = scanner("Enter Id of product you want to Add to Cart: ");
      cart.addProductToCartById(Integer.parseInt(addId));
    }
  }

  private static void cartRemove(Cart cart) {
    if (cart != null) {
      cart.info();
      String removeId = scanner("Enter Id of product you want to Remove from Cart: ");
      if (cart.getCartList().stream()
          .map(Product::getId)
          .toList()
          .contains(Integer.parseInt(removeId))) {
        cart.removeProductFromCartById(Integer.parseInt(removeId));
      } else {
        System.out.println("There is no Product with such Id!!!");
      }
    }
  }

  private static String scanner(String line) {
    System.out.println(line);
    Scanner string = new Scanner(System.in);
    return string.next();
  }
}
