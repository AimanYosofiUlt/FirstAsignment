package com.example.firstdayjava.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BouncyImage extends ImageView {
    public BouncyImage(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
    }

    public BouncyImage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BouncyImage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public boolean performClick() {
        startAnime();
        return super.performClick();
    }

    void startAnime() {
        Animation animation = AnimationUtils.loadAnimation(this.getContext(), com.example.firstdayjava.R.anim.bounce);
        this.startAnimation(animation);
    }
}
