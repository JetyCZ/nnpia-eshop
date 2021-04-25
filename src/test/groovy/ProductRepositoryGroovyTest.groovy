package cz.upce.eshop

import cz.upce.eshop.datafactory.Creator;
import cz.upce.eshop.datafactory.ProductTestDataFactory
import cz.upce.eshop.datafactory.SupplierTestDataFactory;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository
import org.junit.jupiter.api.BeforeEach;

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
@Import([Creator.class])
class ProductRepositoryGroovyTest {

    @Autowired
    private Creator creator;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        productRepository.deleteAll();
    }

    @Test
    void saveProductTest() {

        Product testProduct = new Product(productName: "MyProduct")
        creator.save(testProduct);

        List<Product> all = productRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);

        def readFromDb = productRepository.findById(testProduct.getId()).get()
        Assertions.assertThat(readFromDb.getProductName()).isEqualTo("MyProduct");
        Assertions.assertThat(readFromDb.getDescription()).isEqualTo("Test description");

        Assertions.assertThat(readFromDb.getSupplier().name).isEqualTo("Test name")

    }


}
