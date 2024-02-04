package org.yian.chicksiteblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

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

    @Test
    void test() {
        // 记录消息发送时间戳
        Instant sendTime = Instant.now();
        System.out.println("消息发送时间戳：" + sendTime);

        // 模拟消息传输过程
        try {
            // 等待一段时间表示消息传输过程
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 记录消息接收时间戳
        Instant receiveTime = Instant.now();
        System.out.println("消息接收时间戳：" + receiveTime);

        // 计算消息延迟时间
        long delay = ChronoUnit.MILLIS.between(sendTime, receiveTime);
        System.out.println("消息延迟时间：" + delay + "毫秒");
    }

}
