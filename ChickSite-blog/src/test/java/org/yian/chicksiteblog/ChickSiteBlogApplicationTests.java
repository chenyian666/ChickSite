package org.yian.chicksiteblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ChickSiteBlogApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("12345", "$2a$10$WWAJpQUcDkdBKh0wr0c7x.1n48Y1ZZ/tB7pQcYGS7Edf6FEoajkj2"));
    }

}
