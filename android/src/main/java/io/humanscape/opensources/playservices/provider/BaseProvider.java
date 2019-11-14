package io.humanscape.opensources.playservices.provider;

import com.facebook.react.bridge.ReactApplicationContext;

class BaseProvider {
    ReactApplicationContext context;

    BaseProvider(ReactApplicationContext context) {
        this.context = context;
    }
}
