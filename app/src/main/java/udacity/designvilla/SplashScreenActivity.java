package udacity.designvilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView;

    // Animation
    Animation animation;
    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
       imageView = (ImageView) findViewById(R.id.image_view);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein);
        imageView.startAnimation(animation);
    }
}
