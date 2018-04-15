package udacity.designvilla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.splashscreenjava.R;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.FullScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }
}
