package udacity.designvilla;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.android.splashscreenjava.R;


public class LoginActivity extends AppCompatActivity {

    RelativeLayout rellay, rellay1;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay.setVisibility(View.VISIBLE);
            rellay1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        rellay = (RelativeLayout) findViewById(R.id.rellay);
        rellay = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 3000); //3000 is the timeout for the splash
    }
}
