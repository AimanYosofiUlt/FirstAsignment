package com.example.firstdayjava.ui.activities.splashscreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;
import com.example.firstdayjava.ui.activities.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    @Inject
    SplashActivityViewModel viewModel;

    AppSetting appSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initActionBar();
        initViewModel();
        startSplashScreen();
    }

    private void initViewModel() {
        viewModel.initSetting();
        viewModel.initDoneMDL.observe(this, Void -> viewModel.getLanguage());

        viewModel.settingMDL.observe(this, new Observer<AppSetting>() {
            @Override
            public void onChanged(AppSetting appSetting) {
                SplashActivity.this.appSetting = appSetting;
            }
        });
    }

    private void startSplashScreen() {
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.LANGUAGE, appSetting.getLanguage());
                SplashActivity.this.startActivity(intent);
                finish();
            }
        }.start();
    }

    private void initActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.backColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
