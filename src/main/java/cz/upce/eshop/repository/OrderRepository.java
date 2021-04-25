package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


  @EntityGraph(attributePaths = "orderHasProducts")
  Optional<Order> findById(Long id);

}
