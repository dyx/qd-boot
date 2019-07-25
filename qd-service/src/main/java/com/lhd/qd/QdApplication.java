package com.lhd.qd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lhd
 */
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.lhd.qd.module.**.dao")
@SpringBootApplication
public class QdApplication {

    public static void main(String[] args) {
        SpringApplication.run(QdApplication.class, args);
    }

}
