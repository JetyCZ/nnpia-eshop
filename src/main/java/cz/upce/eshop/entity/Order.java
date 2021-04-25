package cz.upce.eshop.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "order_form")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private StateEnum state;

  @OneToMany(mappedBy = "id")
  private Set<OrderHasProduct> orderHasProducts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public Set<OrderHasProduct> getOrderHasProducts() {
    return orderHasProducts;
  }

  public void setOrderHasProducts(Set<OrderHasProduct> orderHasProducts) {
    this.orderHasProducts = orderHasProducts;
  }
}
