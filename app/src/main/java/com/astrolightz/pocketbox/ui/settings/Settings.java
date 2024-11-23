package com.astrolightz.pocketbox.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.astrolightz.pocketbox.R;

public class Settings extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}