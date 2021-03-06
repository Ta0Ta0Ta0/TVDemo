package com.example.myapplication.practice;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;

public class Emphasize {
    private static final int SCALE_TAG = -10000;

    public static void scaleCenter(View view, float from, float to, long duration) {
        if (view != null && from > 0f && to > 0f && from != to) {
            Object obj = view.getTag(SCALE_TAG);
            if (obj != null && obj instanceof ViewPropertyAnimator) {
                ((ViewPropertyAnimator)obj).cancel();
            }
            ViewPropertyAnimator animator =
                    view.animate().scaleX(to).scaleY(to).setDuration(duration).setInterpolator(new DecelerateInterpolator());


            view.setTag(SCALE_TAG, animator);
            animator.start();
        }
    }
}
