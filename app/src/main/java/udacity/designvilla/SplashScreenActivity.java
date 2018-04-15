package udacity.designvilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

import udacity.designvilla.Util.Tools;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        //customize the status bar color and light
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        ImageView logo = findViewById(R.id.imageView);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
        logo.startAnimation(bounce);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO: place a check to see if user is logged in or not and direct him/her to the respective screen
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.sliding_in, R.anim.sliding_out);
            }
        }, TIME_OUT);
    }
}
