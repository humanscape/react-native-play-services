package io.humanscape.opensources.playservices;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import io.humanscape.opensources.playservices.provider.GoogleAPIProvider;
import io.humanscape.opensources.playservices.provider.NativeActionsProvider;

public class PlayServicesModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final GoogleAPIProvider googleAPIProvider;
    private final NativeActionsProvider nativeActionsProvider;

    public PlayServicesModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        this.googleAPIProvider = new GoogleAPIProvider(reactContext);
        this.nativeActionsProvider = new NativeActionsProvider(reactContext);
    }

    @Override
    public String getName() {
        return "PlayServices";
    }

    @ReactMethod
    public void checkPlayServicesStatus(Promise promise) {
        GooglePlayServicesStatus status = googleAPIProvider.getGooglePlayServicesStatus();
        promise.resolve(status.getStatus());
    }

    @ReactMethod
    public void goToSetting() {
        nativeActionsProvider.goToGooglePlayServicesSetting();
    }

    @ReactMethod
    public void goToMarket() {
        nativeActionsProvider.goToGooglePlayServicesMarketLink();
    }
}
