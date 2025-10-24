package ch.dulce.springjmsbridge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:bridge.xml")
@SpringBootApplication(exclude = {ArtemisAutoConfiguration.class})
public class SpringJmsBridgeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsBridgeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // bridge1.start();
    }

//    @Bean
//    public SSLContext sslContextFactory(SslBundles sslBundles) {
//        SslBundle mqssl = sslBundles.getBundle("mqssl");
//
//        return mqssl.createSslContext();
//    }
}
