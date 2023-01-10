package org.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author Oksiuta Andrii
 * 06.01.2023
 * 17:46
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {

  private List<Product> cartList;

  public void addProductToCartById(int id, ProductRepository repository) {
    cartList.add(repository.getById(id));
  }

  public void removeProductFromCartById(int id) {
    cartList.remove(cartList.get(id));
  }

  public void info() {
    System.out.println("Cart content:");
    for (Product product : cartList) {
      System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());
    }
  }
}
