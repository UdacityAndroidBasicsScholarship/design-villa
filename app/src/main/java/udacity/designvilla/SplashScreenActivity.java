package udacity.designvilla;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

import static java.lang.System.in;

public class SplashScreenActivity extends AppCompatActivity {
    final Animation in = new AlphaAnimation(0.0f, 1.0f);
    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);
        startAnimation();

        ImageView logo = findViewById(R.id.imageView);
        TextView tagline = (TextView)findViewById(R.id.tagline_textView);

        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
        logo.startAnimation(bounce);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.sliding_in, R.anim.sliding_out);
            }
        }, TIME_OUT);

        //Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sliding_in);
        //tagline.startAnimation(fadeIn);
        in.setDuration(3000);
        tagline.startAnimation(in);


    }
    private void startAnimation(){
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
       ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        progressAnimator.setDuration(3000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }
}
