package udacity.designvilla;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;
    private String versionName = BuildConfig.VERSION_NAME;

    RelativeLayout relativeLayout_parent, relativeLayout_parent2;
    ImageView imageView_logo;
    Button enter_btn;
    TextView textView_tag;
    TextView textView_dev;
    TextView textView_ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splashscreen);

        relativeLayout_parent = findViewById(R.id.parent);
        relativeLayout_parent2 = findViewById(R.id.image_bg);
        imageView_logo = findViewById(R.id.img_villa);
        enter_btn = findViewById(R.id.enter_btn);
        textView_tag = findViewById(R.id.tag_textview);
        textView_dev = findViewById(R.id.dev_textView);
        textView_ver = findViewById(R.id.textView_ver);

        textView_ver.setText(versionName);

        Animation fade = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fade_animate);
        imageView_logo.startAnimation(fade);
        textView_tag.startAnimation(fade);
        textView_dev.startAnimation(fade);
        textView_ver.startAnimation(fade);

        Animation slide = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.sliding_in);
        enter_btn.startAnimation(slide);

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}