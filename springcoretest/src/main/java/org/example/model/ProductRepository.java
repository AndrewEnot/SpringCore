package org.example.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

/*
 * @author Oksiuta Andrii
 * 06.01.2023
 * 16:10
 */
@Data
public class ProductRepository {

  private Map<Integer, Product> repository;

  public ProductRepository(List<Product> list) {
    this.repository = list.stream().collect(Collectors.toMap(Product::getId, a -> a));
  }

  public Product getById(int id) {
    if (repository.containsKey(id)) {
      return repository.get(id);
    }
    System.out.println("There is no Product with such Id!!!");
    return null;
  }

  public List<Product> getAllProducts() {
    return repository.values().stream().toList();
  }
}
