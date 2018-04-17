package udacity.designvilla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import com.example.android.splashscreenjava.R;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_sign_in);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);

    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(400);
        slide.setSlideEdge(Gravity.TOP);
        getWindow().setReturnTransition(slide);
    }

}
