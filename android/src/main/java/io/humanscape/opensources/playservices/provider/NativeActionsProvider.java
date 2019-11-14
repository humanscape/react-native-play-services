package io.humanscape.opensources.playservices.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.google.android.gms.common.GoogleApiAvailability;

public class NativeActionsProvider extends BaseProvider {

    public NativeActionsProvider(Context context) {
        super(context);
    }

    private void runActivity(Intent intent) {
        context.startActivity(intent);
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
