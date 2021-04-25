package cz.upce.eshop.datafactory;


import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.Supplier;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTestDataFactory {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierTestDataFactory supplierTestDataFactory;

    public void saveProduct(String productName) {
        Product product = new Product();
        product.setProductName(productName);
        saveProductWithDefaultSupplier(product);
    }

    public void saveProduct(Product product) {
        if (product.getProductName()==null) product.setProductName("Test product");
        if (product.getDescription()==null) product.setDescription("Test description");
        saveProductWithDefaultSupplier(product);
    }

    private void saveProductWithDefaultSupplier(Product product) {
        Supplier testSupplier = supplierTestDataFactory.saveSupplier();
        product.setSupplier(testSupplier);
        productRepository.save(product);
    }



}
