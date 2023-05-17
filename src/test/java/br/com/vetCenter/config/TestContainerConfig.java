package br.com.vetCenter.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.junit.jupiter.Container;

public class TestContainerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    private static MongoDbContainer mongoDbContainer = new MongoDbContainer();

    static {
        mongoDbContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        TestPropertyValues values = TestPropertyValues
                .of("spring.data.mongodb.uri=mongodb://" + mongoDbContainer.getContainerIpAddress()
                        + ":" + mongoDbContainer.getPort() + "/test"

                );
        values.applyTo(applicationContext);
    }
}
