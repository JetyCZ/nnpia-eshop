package cz.upce.eshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  String upload(MultipartFile image);
}
