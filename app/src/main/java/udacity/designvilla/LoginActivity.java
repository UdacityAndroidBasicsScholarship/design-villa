package udacity.designvilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.splashscreenjava.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
}