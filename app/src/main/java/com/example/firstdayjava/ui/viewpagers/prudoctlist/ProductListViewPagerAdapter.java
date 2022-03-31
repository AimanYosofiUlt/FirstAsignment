package com.example.firstdayjava.ui.viewpagers.prudoctlist;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.ui.fragments.product_list.ProductListFragment;
import com.example.firstdayjava.ui.fragments.product_list.ProductListListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListViewPagerAdapter extends FragmentStateAdapter {
    List<Category> categories = new ArrayList<>();
    List<ProductListFragment> fragments = new ArrayList<>();

    public ProductListViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ProductListViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ProductListViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setFragmentsByCategories(List<Category> categories, ProductListListener productListListener) {
        fragments.clear();
        this.categories = categories;
        for (Category category : categories) {
            fragments.add(new ProductListFragment(category,productListListener));
        }
        notifyDataSetChanged();
    }

    public List<Category> getCategories() {
        return categories;
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
