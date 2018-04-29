package udacity.designvilla;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android.splashscreenjava.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import udacity.designvilla.Util.SystemBarColorScheme;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int TIME_OUT = 2000;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //customize the status bar color and light
        SystemBarColorScheme.setSystemBarColor(this, android.R.color.white);
        SystemBarColorScheme.setSystemBarLight(this);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        ImageView logo = findViewById(R.id.logo_image);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
        logo.startAnimation(bounce);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (mUser != null) {
                    intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        }, TIME_OUT);
    }

}
