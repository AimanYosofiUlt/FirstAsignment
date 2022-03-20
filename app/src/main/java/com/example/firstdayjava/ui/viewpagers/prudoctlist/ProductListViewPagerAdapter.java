package com.example.firstdayjava.ui.viewpagers.prudoctlist;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firstdayjava.ui.fragments.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductListViewPagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragments = new ArrayList<>();

    public ProductListViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ProductListViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ProductListViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
