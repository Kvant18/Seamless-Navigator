package org.domino.seamless;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.color.DynamicColors;
import com.yandex.mapkit.MapKitFactory;

import org.domino.seamless.databinding.MainActivityBinding;
import org.domino.seamless.fragments.CameraFragment;
import org.domino.seamless.fragments.HomeFragment;
import org.domino.seamless.fragments.SettingsFragment;

import java.util.Locale;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY);
        super.onCreate(savedInstanceState);
        final SharedPreferences preferences = getSharedPreferences(getString(R.string.configName), Context.MODE_PRIVATE);
        final String locale = preferences.getString(getString(R.string.language_key), "ru");
        setAppLanguage(locale, getResources());

        final MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapKitFactory.initialize(this);
        requestLocationPermission(this);
        requestCameraPermission(this);

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.camera:
                    replaceFragment(new CameraFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(final Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private static void requestLocationPermission(final Activity context) {
        final int code = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        if (code != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        }
    }

    private static void requestCameraPermission(final Activity context) {
        final int code = ContextCompat.checkSelfPermission(context, "android.permission.CAMERA");
        if (code != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{"android.permission.CAMERA"}, 100);
        }
    }

    private static void setAppLanguage(final String language, final Resources resources) {
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, dm);
    }
}