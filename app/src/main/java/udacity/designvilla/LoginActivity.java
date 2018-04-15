package udacity.designvilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.splashscreenjava.R;


public class LoginActivity extends AppCompatActivity {

    /**
     * Function to check if the Email Id entered is in valid format i.e contains @ and .com
     */
    private static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    /**
     * Function to execute when forgot Password button is pressed
     */
    public void forgotPasswordPressed(View view) {
        Toast.makeText(this, "FORGOT PASSWORD PRESSED", Toast.LENGTH_LONG).show();
    }

    /**
     * Function to execute when Sign In button is pressed
     */
    public void signInPressed(View view) {


        // Call Function to Check if the Email ID and Password were entered
        checkUserFields();

    }

    /**
     * Function to execute when Google Sign In button is pressed
     */
    public void signInWithGooglePressed(View view) {
        Toast.makeText(this, "SIGN IN WITH GOOGLE PRESSED", Toast.LENGTH_LONG).show();
    }

    /***
     *  Function to check if the User entered the Email ID and Password
     * */
    public void checkUserFields() {

        // Declare variables to refer Email Id and Password fields
        EditText userEmail_EditText = findViewById(R.id.emailId_EditText);
        EditText userPassword_EditText = findViewById(R.id.password_EditText);

        // Variables to store the values entered in the Email ID and password field and trim the spaces
        String userEmail = String.valueOf(userEmail_EditText.getText()).trim();
        String userPassword = String.valueOf(userPassword_EditText.getText()).trim();

        /*
         * - Check if the values were entered in the fields by the user
         *  - Check if the Email id entered in correct Format
         */

        if ((userEmail.isEmpty()) || (userPassword.isEmpty())) {

            Toast.makeText(this, "Email ID/ Password are required to Sign In!!", Toast.LENGTH_LONG).show();
        } else if (!isValidEmail(userEmail)) {

            Toast.makeText(this, "Please provide Valid Email Id..!!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Valid Email Id..!!", Toast.LENGTH_LONG).show();

        }
    }


}