package cz.upce.eshop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

  @Override
  public String upload(MultipartFile image) {

    try {
      Path path = Paths.get("C:\\Users\\zuzka\\IdeaProjects\\nnpia-eshop\\images\\" + image.getOriginalFilename());
      Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image.getOriginalFilename();
  }
}
