package com.alex.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.alex.androidapiguides.R;

/**
 * Created by alex on 15-4-27.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
