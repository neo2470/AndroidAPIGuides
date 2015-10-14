package com.alex.androidapiguides;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.alex.fragment.SettingsFragment;

/**
 * Created by alex on 15-4-24.
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeTitle();

        getFragmentManager().beginTransaction()
                .replace(R.id.settings_content, new SettingsFragment())
                .commit();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }
}
