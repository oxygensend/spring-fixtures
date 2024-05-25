package com.oxygensend.springfixtures;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

final class FixturesContextRefreshedListener {
    private final FixturesLoader loader;

    FixturesContextRefreshedListener(FixturesLoader loader) {
        this.loader = loader;
    }

    @EventListener(ContextRefreshedEvent.class)
    void listen(ContextRefreshedEvent event) {
        loader.load();
    }
}
