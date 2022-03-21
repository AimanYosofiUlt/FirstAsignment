package com.example.firstdayjava.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.firstdayjava.R;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String LANGUAGE = "language";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(getSupportActionBar()).hide();

//        Window window = this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(this, R.color.backColor));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (getIntent().getStringExtra(LANGUAGE) != null) {
   //         changeLang(getIntent().getStringExtra(LANGUAGE));
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    void changeLang(String langCode) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("lang", langCode);
        Locale locale = new Locale(lang);
    //    Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}