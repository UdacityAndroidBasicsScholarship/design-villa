package udacity.designvilla;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;


public class LoginActivity extends AppCompatActivity {

    private Button button;
    private TextView text;
    private ViewGroup transitionsContainer;
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//this lines are for gadient background
        relativeLayout = (RelativeLayout)findViewById(R.id.login_screen_relative_layout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);




        transitionsContainer = (ViewGroup)findViewById(R.id.transitions_container);
        text = (TextView) transitionsContainer.findViewById(R.id.logging_in_textview);
        button = (Button) transitionsContainer.findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

}