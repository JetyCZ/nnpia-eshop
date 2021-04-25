package cz.upce.eshop.dto;

import org.springframework.web.multipart.MultipartFile;

public class Images {
  MultipartFile image;
  public MultipartFile getImage() {
    return image;
  }
  public void setImage(MultipartFile image) {
    this.image = image;
  }
}