package udacity.designvilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.splashscreenjava.R;
import com.google.android.gms.common.SignInButton;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SignInButton mGoogleSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mGoogleSignInButton = findViewById(R.id.google_sign_in_button);
        mGoogleSignInButton.setSize(SignInButton.SIZE_WIDE);
        mGoogleSignInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.google_sign_in_button) {
            Toast.makeText(this, R.string.login_message, Toast.LENGTH_SHORT).show();
        }
    }
}