package com.oxygensend.springfixtures;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class FixturesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixturesLoader.class);

    private final List<Fixture> fixtures;

    FixturesLoader(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    private static class LoadingDuration {

        private LoadingDuration() {

        }

        public static long catchDuration(Runnable runnable) {
            var startTime = System.nanoTime();
            runnable.run();
            var endTime = System.nanoTime();
            return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        }

    }

    void load() {
        fixtures.stream()
                .sorted(Comparator.comparingInt(fixture -> fixture.type().priority()))
                .filter(Fixture::isEnabled)
                .forEach(this::load);
    }

    void load(Fixture fixture) {
        LOGGER.info("Loading fixtures {}", fixture.collectionName());
        long duration = LoadingDuration.catchDuration(fixture::load);
        LOGGER.info("Loading fixtures {} finished. It took {} to load all fixtures", fixture.collectionName(), duration);
    }

}
