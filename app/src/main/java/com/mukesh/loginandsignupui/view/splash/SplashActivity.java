package com.mukesh.loginandsignupui.view.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mukesh.loginandsignupui.R;
import com.mukesh.loginandsignupui.view.signin.SignInActivity;

/**
 * Simple Splash activity with some Transition
 * You can validate your Login details and send to
 * activity accordingly
 */
public class SplashActivity extends AppCompatActivity {

    //Variables
    private Animation topAnim, bottomAnim, rightToCenter;

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();
    private ImageView appLogo;
    private TextView appTitle;
    private TextView tagLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To hide Status bar or we can do with Theme also
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initAnimation();
        iniView();
        sendToNextActivity();
    }

    //Initialisation of all the views
    private void iniView() {

        appLogo = findViewById(R.id.image_logo);
        appTitle = findViewById(R.id.title);
        tagLine = findViewById(R.id.tag_line);

        //Setting the Animation
        appLogo.setAnimation(rightToCenter);
        appTitle.setAnimation(topAnim);
        tagLine.setAnimation(bottomAnim);
    }

    private void initAnimation() {
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        rightToCenter = AnimationUtils.loadAnimation(this, R.anim.right_to_center);
    }

    private void sendToNextActivity() {
        handler.postDelayed(myRunnable, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (handler != null) {
            handler.removeCallbacks(myRunnable);
        }
        finish();
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {

            //Attach all the elements those you want to animate in design
            //Pair Class uses same key value type pair
            //Key is the View to be animated while transition
            // transitionName matches with SignInActivity XML elements
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(appLogo, "app_icon");
            pairs[1] = new Pair<View, String>(appTitle, getString(R.string.hello_there_welcome_back));
            pairs[2] = new Pair<View, String>(tagLine, getString(R.string.sign_in_to_continue));

            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    SplashActivity.this, pairs);
            startActivity(intent, options.toBundle());

            onBackPressed();
        }
    }
}
