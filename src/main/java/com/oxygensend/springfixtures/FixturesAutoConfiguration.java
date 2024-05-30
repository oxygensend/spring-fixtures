package com.oxygensend.springfixtures;

import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(FixturesProperties.class)
@AutoConfiguration
public class FixturesAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "fixtures.enabled", havingValue = "true")
    FixturesLoader fixturesLoader(List<Fixture> fixtures) {
        return new FixturesLoader(fixtures);
    }

    @Bean
    @ConditionalOnExpression("'${fixtures.enabled}' == 'true' && '${fixtures.trigger-event}' == 'APPLICATION_READY'")
    FixturesApplicationReadyListener applicationReadyListener(FixturesLoader loader) {
        return new FixturesApplicationReadyListener(loader);
    }

    @Bean
    @ConditionalOnExpression("'${fixtures.enabled}'== 'true' && '${fixtures.trigger-event}' == 'CONTEXT_REFRESHED'")
    FixturesContextRefreshedListener contextRefreshedListener(FixturesLoader loader) {
        return new FixturesContextRefreshedListener(loader);
    }

    @Bean
    @ConditionalOnProperty(name = "fixtures.enabled", havingValue = "true")
    FixturesFakerProvider fixturesFakerProvider(FixturesProperties properties) {
        return new FixturesFakerProvider(properties, 100);
    }
}
