package udacity.designvilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android.splashscreenjava.R;
import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_splashscreen);

        ImageView splashLogo = findViewById(R.id.splash_logo);
        LinearLayout splashText = findViewById(R.id.splash_text);
        StartSmartAnimation.startAnimation(splashLogo, AnimationType.Wave, TIME_OUT, 0, false);
        StartSmartAnimation.startAnimation(splashText, AnimationType.ZoomIn, TIME_OUT, 0, false);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.sliding_in, R.anim.sliding_out);
            }
        }, TIME_OUT);
    }
}
