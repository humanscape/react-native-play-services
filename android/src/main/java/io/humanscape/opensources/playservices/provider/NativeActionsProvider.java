package io.humanscape.opensources.playservices.provider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.common.GoogleApiAvailability;

public class NativeActionsProvider extends BaseProvider {

    public NativeActionsProvider(ReactApplicationContext context) {
        super(context);
    }

    private void runActivity(Intent intent) {
        Activity currentActivity = context.getCurrentActivity();
        if (currentActivity == null) {
            throw new NullPointerException();
        }
        currentActivity.startActivity(intent);
    }

    public void goToGooglePlayServicesSetting() {
        Uri uri = Uri.parse("package:" + GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(uri);

        this.runActivity(intent);
    }

    public void goToGooglePlayServicesMarketLink() {
        Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=" + GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        this.runActivity(intent);
    }
}
