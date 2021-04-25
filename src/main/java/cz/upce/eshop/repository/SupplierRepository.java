package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.Supplier;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {



}
