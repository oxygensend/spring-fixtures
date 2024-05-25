package com.oxygensend.springfixtures;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fixtures")
record FixturesProperties(Boolean enabled,
                          TriggerEvent triggerEvent) {
    FixturesProperties {
        enabled = enabled != null && enabled;
        triggerEvent = triggerEvent == null ? TriggerEvent.CONTEXT_REFRESHED : triggerEvent;
    }
}
