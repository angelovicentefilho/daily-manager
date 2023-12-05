package br.com.jtech.services.daily.manager;

import br.com.jtech.services.daily.manager.config.infra.redis.RedisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@Import(RedisConfiguration.class)
public class StartDailyManager {

    public static void main(String[] args) {
        SpringApplication.run(StartDailyManager.class, args);
    }

}
