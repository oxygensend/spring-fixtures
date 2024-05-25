package com.oxygensend.springfixtures;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

final class FixturesApplicationReadyListener {
    private final FixturesLoader loader;

    FixturesApplicationReadyListener(FixturesLoader loader) {
        this.loader = loader;
    }

    @EventListener(ApplicationReadyEvent.class)
    void listen(ApplicationReadyEvent event) {
        loader.load();
    }
}
