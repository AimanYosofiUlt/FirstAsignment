package com.example.firstdayjava.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentMainBinding;
import com.example.firstdayjava.ui.viewpagers.mainviewpager.MainViewPagerAdapter;

public class MainFragment extends Fragment {
    FragmentMainBinding bd;
    MainViewPagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentMainBinding.inflate(inflater, container, false);

        init();
        return bd.getRoot();
    }

    private void init() {
        initBottomNav();

        adapter = new MainViewPagerAdapter(this);
        bd.mainVP.setAdapter(adapter);
        bd.mainVP.setUserInputEnabled(false);

        adapter.addFragment(new CategoryFragment());
    }

    private void initBottomNav() {
        bd.meowBNav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bd.meowBNav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_orders));
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