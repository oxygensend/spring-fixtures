package com.oxygensend.springfixtures;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class FixturesFakerProvider {

    private final Faker faker;
    private final Random random;

    FixturesFakerProvider(FixturesProperties properties, int seed) {
        this.random = new Random(seed);
        this.faker = Faker.instance(Locale.of(properties.fakerLocale()), random);
    }

    public Faker faker() {
        return faker;
    }


    public Random random() {
        return random;
    }

}
