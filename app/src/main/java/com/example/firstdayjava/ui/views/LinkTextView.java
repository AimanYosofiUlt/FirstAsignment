package com.example.firstdayjava.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstdayjava.R;

public class LinkTextView extends androidx.appcompat.widget.AppCompatTextView {
    public LinkTextView(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
    }

    public LinkTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public boolean performClick() {
        startAnime();
        return super.performClick();
    }

    void startAnime() {
        Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.link);
        this.startAnimation(animation);
    }
}
