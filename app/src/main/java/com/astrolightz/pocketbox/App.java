package com.astrolightz.pocketbox;

import android.app.Application;
import android.os.Build;

import com.google.android.material.color.DynamicColors;

public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Dynamic Colors if device supports it
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
    }
}
