package cz.upce.eshop.service;

import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.OrderHasProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.StateEnum;
import cz.upce.eshop.repository.OrderHasProductRepository;
import cz.upce.eshop.repository.OrderRepository;
import cz.upce.eshop.repository.ProductRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private Map<Product, Integer> cart;

  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final OrderHasProductRepository orderHasProductRepository;

  public ShoppingCartServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, OrderHasProductRepository orderHasProductRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
    this.orderHasProductRepository = orderHasProductRepository;
    cart = new HashMap<>();
  }

  @Override
  public void add(Long id) {
    Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    if (cart.containsKey(product)) {
      cart.replace(product, cart.get(product)+1);
    } else {
      cart.put(product, 1);
    }
  }

  @Override
  public void remove(Long id) {
    Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    if (cart.containsKey(product)) {
      if(cart.get(product) > 1) {
        cart.replace(product, cart.get(product) -1);
      } else {
        cart.remove(product);
      }
    }
  }

  @Override
  public Map<Product, Integer> getCart() {
    return cart;
  }

  @Override
  public void checkout() {

    Order order = new Order();
    order.setState(StateEnum.NEW);
    orderRepository.save(order);

    for (Entry<Product, Integer> entry : cart.entrySet()) {
      OrderHasProduct orderHasProduct = new OrderHasProduct();
      orderHasProduct.setOrder(order);
      orderHasProduct.setProduct(entry.getKey());
      orderHasProduct.setAmount(entry.getValue());
      orderHasProductRepository.save(orderHasProduct);
    }

    cart.clear();

  }
}
