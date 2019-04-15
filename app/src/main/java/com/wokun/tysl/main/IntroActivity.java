package com.wokun.tysl.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.annotations.Nullable;
import com.github.paolorotolo.appintro.AppIntro;
import com.shantoo.common.utils.AppManager;
import com.wokun.tysl.R;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //addSlide(new FirstFragment());
        //addSlide(new SecondFragment());
        //addSlide(new ThirdFragment());

        addSlide(SampleSlide.newInstance(R.layout.activity_intro1));
        addSlide(SampleSlide.newInstance(R.layout.activity_intro2));
        addSlide(SampleSlide.newInstance(R.layout.activity_intro3));
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        // addSlide(AppIntroFragment.newInstance("title", "description", R.drawable.ic_intro1, UIUtil.getColor(R.color.colorWhite)));
        // addSlide(AppIntroFragment.newInstance("title", "description", R.drawable.ic_intro2, UIUtil.getColor(R.color.colorWhite)));
        // addSlide(AppIntroFragment.newInstance("title", "description", R.drawable.ic_intro3, UIUtil.getColor(R.color.colorWhite)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#ffffff"));
        setSeparatorColor(Color.TRANSPARENT);

        // Hide Skip/Done button.
        //showSkipButton(false);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    public void goMain(View view){
        finish();
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
