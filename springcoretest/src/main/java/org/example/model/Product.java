package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 06.01.2023
 * 16:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
  private int id;
  private String name;
  private double price;

}
