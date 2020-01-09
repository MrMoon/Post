package com.moon.squad.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

 @Override
 public void configureRepositoryRestConfiguration(@NotNull RepositoryRestConfiguration config) {
    config.getCorsRegistry().addMapping("/**").allowedOrigins("http://localhost:4200");
  }
}
