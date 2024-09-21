package org.domino.seamless.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import org.domino.seamless.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        ListPreference languageList = findPreference(getResources().getString(R.string.language_key));
        assert languageList != null;
        languageList.setOnPreferenceChangeListener((preference, newValue) -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.configName), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.language_key), String.valueOf(newValue));
            editor.apply();
            return true;
        });

    }
}