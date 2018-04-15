package udacity.designvilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        // Declaration of ImageView containing App Name in the Splash screen
        ImageView namelogo = findViewById(R.id.name_ImageView);

        // Declaration of Animation effect in splash screen
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);

        // To give bounce effect to App name in Splash screen
        namelogo.startAnimation(bounce);

        //Once animation is complete Take the user to Login Screen
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


