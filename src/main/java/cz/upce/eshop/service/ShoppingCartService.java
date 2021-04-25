package cz.upce.eshop.service;

import cz.upce.eshop.entity.Product;
import java.util.Map;

public interface ShoppingCartService {

  void add(Long id);

  void remove(Long id);

  Map<Product, Integer> getCart();

  void checkout();
}
