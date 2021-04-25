package cz.upce.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@EnableWebMvc
public class ResourceImageConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      String buildClassesJavaMain = this.getClass().getResource("/").getFile();
      String projectRoot = new File(buildClassesJavaMain)
          .getParentFile()
          .getParentFile()
          .getParentFile()
          .getParentFile()
            .getAbsolutePath();
      String imagesPath = "file:" + projectRoot + "\\images\\";

      registry.addResourceHandler("/images/**")
          .addResourceLocations(imagesPath);

  }
}
