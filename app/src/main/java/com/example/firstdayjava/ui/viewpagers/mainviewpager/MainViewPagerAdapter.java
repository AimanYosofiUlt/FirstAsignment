package com.example.firstdayjava.ui.viewpagers.mainviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firstdayjava.ui.fragments.category.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragments = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MainViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CategoryFragment();
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
