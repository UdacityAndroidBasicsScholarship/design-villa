package udacity.designvilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android.splashscreenjava.R;

import udacity.designvilla.Util.Tools;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //customize the status bar color and light
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        ImageView logo = findViewById(R.id.logo_image);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
        logo.startAnimation(bounce);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO: place a check to see if user is logged in or not and direct him/her to the respective screen
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                        (SplashScreenActivity.this, findViewById(R.id.logo_image), getResources().getString(R.string.transition_element));

                startActivity(intent, activityOptionsCompat.toBundle());
            }
        }, TIME_OUT);
    }
}
