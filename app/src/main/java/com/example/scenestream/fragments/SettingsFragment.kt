package com.example.scenestream.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.scenestream.*
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
    }
}

