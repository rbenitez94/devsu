package org.devsu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class DevsuApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(DevsuApplication.class, args);
    }
}