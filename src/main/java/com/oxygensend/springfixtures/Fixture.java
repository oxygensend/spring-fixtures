package com.oxygensend.springfixtures;

public interface Fixture {

    /**
     * Identifies fixture type that is used to specify fixtures loading order
     */
    default FixtureType type() {
        return FixtureType.BUSINESS_OBJECT;
    }

    /**
     * Loads fixtures to the database on startup
     */
    void load();

    /**
     * Fixtures are loaded only if bellowed method returns true
     */
    boolean isEnabled();

    String collectionName();

}
