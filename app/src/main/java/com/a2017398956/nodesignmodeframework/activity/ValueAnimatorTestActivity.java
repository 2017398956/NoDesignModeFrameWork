package com.a2017398956.nodesignmodeframework.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.view.BaseActivity;

public class ValueAnimatorTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_test);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f , 1f) ;
        valueAnimator.addListener(new Animator.AnimatorListener(){
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(300) ;
        valueAnimator.start();
    }
}
