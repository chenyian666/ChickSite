package org.yian.chicksiteblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.yian.chicksiteblog.dao")
public class ChickSiteBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChickSiteBlogApplication.class, args);
    }

}
