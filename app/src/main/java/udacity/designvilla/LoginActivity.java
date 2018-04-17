package udacity.designvilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.splashscreenjava.R;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);

    }
}