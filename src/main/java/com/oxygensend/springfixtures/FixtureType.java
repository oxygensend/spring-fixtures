package com.oxygensend.springfixtures;

public enum FixtureType {
    DICTIONARY(1),
    BUSINESS_OBJECT(2);

    private final int priority;

    FixtureType(int priority) {
        this.priority = priority;
    }

    int priority() {
        return priority;
    }
}
