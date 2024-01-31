package org.yian.chicksiteblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootTest
class ChickSiteBlogApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("12345", "$2a$10$WWAJpQUcDkdBKh0wr0c7x.1n48Y1ZZ/tB7pQcYGS7Edf6FEoajkj2"));
    }

    @Test
    void inetAddressDemo() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostName());
        System.out.println(Arrays.toString(localHost.getAddress()));
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getCanonicalHostName());
        System.out.println(InetAddress.getByName("poetize.cn"));
        System.out.println(Arrays.toString(InetAddress.getAllByName("poetize.cn")));
        InetAddress address = InetAddress.getByName("poetize.cn");
        System.out.println(address.getHostName());
        System.out.println(address.getHostAddress());
    }


}
