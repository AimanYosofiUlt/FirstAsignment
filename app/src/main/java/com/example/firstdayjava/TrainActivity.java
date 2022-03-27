package com.example.firstdayjava;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.firstdayjava.databinding.FragmentMainBinding;
import com.example.firstdayjava.ui.viewpagers.adsviewpager.AdsPagerAdapter;
import com.example.firstdayjava.ui.views.CategoryView.CategoryAdapter;

import java.util.Objects;


public class TrainActivity extends AppCompatActivity {

    FragmentMainBinding bd;
    CategoryAdapter adapter;

    AdsPagerAdapter pagerAdapter;
    private Handler sliderHandler = new Handler();

    private int[] drawables = {R.drawable.image, R.drawable.image2, R.drawable.image3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = FragmentMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.backColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


    }

    private void initBottomNav() {
        bd.meowBNav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bd.meowBNav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_cardes));
        bd.meowBNav.add(new MeowBottomNavigation.Model(3, R.drawable.ic_shop));
        bd.meowBNav.add(new MeowBottomNavigation.Model(4, R.drawable.ic_menu));


        bd.meowBNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                bd.meowBNav.show(item.getId(), true);
            }
        });

        bd.meowBNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bd.meowBNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bd.meowBNav.show(1, false);
    }




}