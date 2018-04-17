package udacity.designvilla;

        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.content.Intent;
        import android.widget.VideoView;

        import com.example.android.splashscreenjava.R;

public class SplashScreenActivity extends AppCompatActivity {

    public final static int TIME_OUT = 3000;
    private VideoView videoView;

    //Additional

    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * It is creating NullPointer Error
         */
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);



        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

        /*
         * using intro refer to ID
         * video refers to a video for splash screen
         */

        videoView = findViewById(R.id.intro);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setLooping(true);
                // We then seek to the current posistion if it has been set and play the video.
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });

//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });

    }

    /*
     * This method solves video problem for Splash Screen as
     * on minimizing and then re-opening app from logs it leads to no video sometimes
     * so this method prevent that.
     */

    public void onResume() {
            super.onResume();
            videoView.start();
        }

    }


