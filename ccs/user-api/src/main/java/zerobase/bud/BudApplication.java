package zerobase.bud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "zerobase.bud" )
@EnableScheduling
public class BudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudApplication.class, args);
    }

}
