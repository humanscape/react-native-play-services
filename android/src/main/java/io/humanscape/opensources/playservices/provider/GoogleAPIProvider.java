package io.humanscape.opensources.playservices.provider;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import io.humanscape.opensources.playservices.GooglePlayServicesStatus;

public class GoogleAPIProvider extends BaseProvider {
    private boolean isGmsEnabled;

    public GoogleAPIProvider(ReactApplicationContext context) {
        super(context);
        this.initializeGMSStatus();
    }

    private void initializeGMSStatus() {
        try {
            ApplicationInfo gmsInfo = context.getPackageManager().getApplicationInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0);
            isGmsEnabled = gmsInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            isGmsEnabled = false;
        }
    }

    public GooglePlayServicesStatus getGooglePlayServicesStatus() {
        if (!isGmsEnabled) {
            return GooglePlayServicesStatus.GMS_DISABLED;
        }

        int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (result != ConnectionResult.SUCCESS) {
            return GooglePlayServicesStatus.GMS_NEED_UPDATE;
        }
        return GooglePlayServicesStatus.AVAILABLE;
    }
}
