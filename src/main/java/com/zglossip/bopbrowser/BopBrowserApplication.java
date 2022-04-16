package com.zglossip.bopbrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BopBrowserApplication extends SpringBootServletInitializer {

  public static void main(final String[] args) {
    SpringApplication.run(BopBrowserApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(BopBrowserApplication.class);
  }
}
