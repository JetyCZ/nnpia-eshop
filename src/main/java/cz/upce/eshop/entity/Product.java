package cz.upce.eshop.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String productName;

  @Column(columnDefinition = "text")
  private String description;

  @Column
  private String pathToImage;

  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
  private Set<OrderHasProduct> productInOrders;

  @ManyToOne
  private Supplier supplier;

  public String getProductName() {
    return productName;
  }

  public void setProductName(String name) {
    this.productName = name;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPathToImage() {
    return pathToImage;
  }

  public void setPathToImage(String pathToImage) {
    this.pathToImage = pathToImage;
  }

  public Set<OrderHasProduct> getProductInOrders() {
    return productInOrders;
  }

  public void setProductInOrders(Set<OrderHasProduct> productInOrders) {
    this.productInOrders = productInOrders;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(id, product.id) && Objects.equals(productName, product.productName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productName);
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Supplier getSupplier() {
    return supplier;
  }
}
