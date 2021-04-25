package cz.upce.eshop;

import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.service.ShoppingCartService;
import cz.upce.eshop.service.ShoppingCartServiceImpl;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class ShoppingCartTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ShoppingCartService shoppingCartService;

  @Test
  void addOneToShoppingCart() {

    Product product = new Product();
    product.setProductName("MyProduct");
    productRepository.save(product);
    List<Product> all = productRepository.findAll();

    Long productId = all.get(0).getId();

    shoppingCartService.add(productId);


    // počet prvků v košíku = 1
    Assertions.assertThat(shoppingCartService.getCart().size()).isEqualTo(1);
    // obsahuje právě vložený produkt
    Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isTrue();
    // obsahuje vložený produkt v počtu = 1
    Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);


    shoppingCartService.add(productId);
    Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

    shoppingCartService.add(productId);
    Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(3);


    shoppingCartService.remove(productId);
    Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

    shoppingCartService.remove(productId);
    Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);

    shoppingCartService.remove(productId);
    Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isFalse();







  }

}
