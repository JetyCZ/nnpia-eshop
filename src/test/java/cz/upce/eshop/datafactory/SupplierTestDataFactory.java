package cz.upce.eshop.datafactory;


import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.Supplier;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierTestDataFactory {


    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier saveSupplier() {
        Supplier testSupplier = new Supplier();
        testSupplier.setName("Test supplier");
        supplierRepository.save(testSupplier);
        return testSupplier;
    }

}
