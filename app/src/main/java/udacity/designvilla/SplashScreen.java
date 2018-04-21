package udacity.designvilla;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.android.splashscreenjava.R;

public class SplashScreen extends AppCompatActivity {
    ImageView l;
    ActivityOptions activityOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        l=(ImageView) findViewById(R.id.imageView);
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        final TranslateAnimation upanim=new TranslateAnimation(0,0,0,10);
        final TranslateAnimation downanim=new TranslateAnimation(0,0,10,0);
        upanim.setDuration(600);
        downanim.setDuration(600);
        l.setAnimation(upanim);
        upanim.start();

        upanim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                l.setAnimation(downanim);
                downanim.start();

            }
        });
        downanim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                l.setAnimation(upanim);
                upanim.start();


            }

        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                trans();
            }
        },2400);


    }
public void trans(){
    Intent i=new Intent(SplashScreen.this,LoginActivity.class);
    activityOptions=ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,(ImageView)findViewById(R.id.imageView),"layout");
    startActivity(i,activityOptions.toBundle());

}}
