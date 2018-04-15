package udacity.designvilla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;
import com.github.florent37.shapeofview.shapes.ArcView;


public class LauncherActivity extends AppCompatActivity /*implements GestureDetector.OnGestureListener*/ {


    TextView madeBy;
    TextView tag_line;
    TextView swipe_message;
    ImageView logo;
    ArcView arcView;

//    GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        logo = findViewById(R.id.logo);
        tag_line = findViewById(R.id.tag_line);
        madeBy = findViewById(R.id.made_by);
        swipe_message = findViewById(R.id.swipe);
        arcView = findViewById(R.id.swipe_screen);

        String madeByString = "Made with ❤ by Codevegers";

        madeBy.setText(madeByString);
        logo.setImageResource(R.drawable.sample_logo);
        tag_line.setText(R.string.tag_line);
        swipe_message.setText(R.string.swipe_down_to_login);

        startAnimationLaunch();


        arcView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Intent intent = new Intent(LauncherActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

//        gestureDetector = new GestureDetector(this, this);

    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        gestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onDown(MotionEvent motionEvent) {
//        onDrawerTouchAnimation();
//        return true;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent motionEvent) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent motionEvent) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent motionEvent) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        return false;
//    }
//
//    private void onDrawerTouchAnimation(){
//
//    }

    private void startAnimationLaunch() {
        Animation animationTray = AnimationUtils.loadAnimation(this, R.anim.animation_launcher);
        Animation elements = AnimationUtils.loadAnimation(this, R.anim.animation_on_launcher_start);

//        animationTray.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//        });

        madeBy.clearAnimation();
        arcView.clearAnimation();

        madeBy.startAnimation(elements);
        arcView.startAnimation(animationTray);

    }


}




/*

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            // moveTo(account)
        } else {
            // signIn()
        }


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
*/



  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    protected void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
*/
