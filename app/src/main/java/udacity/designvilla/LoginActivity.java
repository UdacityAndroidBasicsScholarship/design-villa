package udacity.designvilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.splashscreenjava.R;
import com.google.android.gms.common.SignInButton;

import udacity.designvilla.Util.SystemBarColorScheme;


public class LoginActivity extends AppCompatActivity {

    private SignInButton googleSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //customize the status bar color and light
        SystemBarColorScheme.setSystemBarColor(this, android.R.color.white);
        SystemBarColorScheme.setSystemBarLight(this);

        googleSignInBtn = findViewById(R.id.sign_in_button);
        //customize the button
        googleSignInBtn.setSize(SignInButton.SIZE_WIDE);
        googleSignInBtn.setColorScheme(SignInButton.COLOR_DARK);

        //TODO: Implement Google Sign-In logic
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}