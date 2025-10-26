package com.adphilip.spring.mssql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootSqlServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSqlServerApplication.class, args);
  }

}
