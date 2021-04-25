package cz.upce.eshop;

import cz.upce.eshop.datafactory.ProductTestDataFactory;
import cz.upce.eshop.datafactory.SupplierTestDataFactory;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({ProductTestDataFactory.class, SupplierTestDataFactory.class})


class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  ProductTestDataFactory productTestDataFactory;

  @Test
  void saveProductTest() {

    productTestDataFactory.saveProduct("MyProduct");
    List<Product> all = productRepository.findAll();
    Assertions.assertThat(all.size()).isEqualTo(1);

  }


}
