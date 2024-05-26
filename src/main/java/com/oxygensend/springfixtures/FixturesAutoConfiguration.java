package com.oxygensend.springfixtures;

import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty(name = "fixtures.enabled", havingValue = "true")
@EnableConfigurationProperties(FixturesProperties.class)
@AutoConfiguration
public class FixturesAutoConfiguration {

    @Bean
    FixturesLoader fixturesLoader(List<Fixture> fixtures) {
        return new FixturesLoader(fixtures);
    }

    @Bean
    @ConditionalOnProperty(name = "fixtures.trigger-event", havingValue = "APPLICATION_READY")
    FixturesApplicationReadyListener applicationReadyListener(FixturesLoader loader) {
        return new FixturesApplicationReadyListener(loader);
    }

    @Bean
    @ConditionalOnProperty(name = "fixtures.trigger-event", havingValue = "CONTEXT_REFRESHED")
    FixturesContextRefreshedListener contextRefreshedListener(FixturesLoader loader) {
        return new FixturesContextRefreshedListener(loader);
    }
}
