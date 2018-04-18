package udacity.designvilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;


public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
     private TextView info;
    private Button login;
    private int noOfAttempts = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        name = (EditText)findViewById(R.id.loginName);
        password = (EditText)findViewById(R.id.password);
           info = (TextView)findViewById(R.id.info);
        login = (Button)findViewById(R.id.Loginbutton);

           info.setText("No. of attempts remaining : 3");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });
    }

    private void validate (String userName , String userPassword){
        if((userName.equals("admin") ) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(LoginActivity.this, afterLogin.class);
            startActivity(intent);
        }
        else{
            noOfAttempts--;

                   info.setText("No. of attempts remaining : " + String.valueOf(noOfAttempts));

            if(noOfAttempts == 0){
                login.setEnabled(false);

            }
        }

    }
}