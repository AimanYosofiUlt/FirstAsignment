package com.example.firstdayjava.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentMainBinding;
import com.example.firstdayjava.ui.fragments.cart.CartFragment;
import com.example.firstdayjava.ui.fragments.category.CategoryFragment;
import com.example.firstdayjava.ui.fragments.order.OrderPageFragment;
import com.example.firstdayjava.ui.fragments.setting.SettingFragment;
import com.example.firstdayjava.ui.viewpagers.mainviewpager.MainViewPagerAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {
    FragmentMainBinding bd;
    MainViewPagerAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentMainBinding.inflate(inflater, container, false);


        initBottomNav();
        init();
        initEvent();
        return bd.getRoot();
    }

    private void initEvent() {

    }

    private void init() {
        adapter = new MainViewPagerAdapter(this);
        bd.mainVP.setAdapter(adapter);
        bd.mainVP.setUserInputEnabled(false);

        CategoryFragment categoryFragment = new CategoryFragment(category -> {
            Toast.makeText(requireContext(), "Hi:" + category.getCategoryName(), Toast.LENGTH_SHORT).show();
            NavHostFragment
                    .findNavController(requireParentFragment())
                    .navigate(
                            MainFragmentDirections
                                    .actionMainFragmentToPrudoctListFragment()
                                    .setCategoryCode(category.getCategoryCode())
                    );
        });

        adapter.addFragment(categoryFragment);
        adapter.addFragment(new OrderPageFragment());
        adapter.addFragment(new CartFragment());
        adapter.addFragment(new SettingFragment());
    }

    private void initBottomNav() {
        bd.meowBNav.add(new MeowBottomNavigation.Model(0, R.drawable.ic_home));
        bd.meowBNav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_orders));
        bd.meowBNav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_shop));
        bd.meowBNav.add(new MeowBottomNavigation.Model(3, R.drawable.ic_menu));


        bd.meowBNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                bd.meowBNav.show(item.getId(), true);
                bd.mainVP.setCurrentItem(item.getId());
            }
        });

        bd.meowBNav.setOnShowListener(item -> {
            // your codes
        });

        bd.meowBNav.setOnReselectListener(item -> {
            // your codes
        });

        bd.meowBNav.show(0, false);
    }

}