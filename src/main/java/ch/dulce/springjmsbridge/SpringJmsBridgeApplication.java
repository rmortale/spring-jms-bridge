package ch.dulce.springjmsbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:factories.xml", "classpath:bridge02.xml"})
@SpringBootApplication(exclude = {ArtemisAutoConfiguration.class})
public class SpringJmsBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsBridgeApplication.class, args);
    }

}
