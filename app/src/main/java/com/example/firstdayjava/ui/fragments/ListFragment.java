package com.example.firstdayjava.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentListBinding;
import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.ui.views.UserView.UserAdapter;
import com.example.firstdayjava.ui.views.UserView.UserViewListener;
import com.example.firstdayjava.ui.fragments.main.MainFragmentViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment extends Fragment implements UserViewListener {

    FragmentListBinding bd;
    MainFragmentViewModel viewModel;

    UserAdapter adapter;
    ArrayAdapter<String> phoneAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentListBinding.inflate(inflater, container, false);
        initViewModel();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);

        initModelView();

//        viewModel.users.observe(requireActivity(), users -> {
//            adapter.setList(users);
//            adapter.notifyDataSetChanged();
//        });
    }

    private void initModelView() {
        adapter = new UserAdapter(this);
        bd.userListRV.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(requireActivity(), 2);
        bd.userListRV.setLayoutManager(manager);


        initEvent();
    }

    private void initEvent() {

        bd.button.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_mainRragment_to_editFragment);
        });
    }


    @Override
    public void onDeleteReq(Users user) {
//        viewModel.deleteUser(user);
    }

    @Override
    public void onUpdateReq(Users user) {
//        viewModel.updateUser(user);
    }
}