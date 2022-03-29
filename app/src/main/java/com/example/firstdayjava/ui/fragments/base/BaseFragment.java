package com.example.firstdayjava.ui.fragments.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected abstract void initViewModel();

    protected abstract void initModelView();

    protected abstract void initEvent();
}
