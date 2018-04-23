package udacity.designvilla;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private Button mBackButton, mNextButton;
    private TextView[] mDotsTextView;
    private int mCurrentPage;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_onboarding);

        //assigning id to the view pager
        mViewPager = findViewById(R.id.view_pager);
        //assigning id to the linear layout
        mLinearLayout = findViewById(R.id.dots_layout);
        //assigning id to the back button
        mBackButton = findViewById(R.id.back_button);
        //assigning id to the next button
        mNextButton = findViewById(R.id.next_button);

        //creating an instance of the ViewPagerAdapter class
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        //setting the adapter to the viewpager
        mViewPager.setAdapter(viewPagerAdapter);
        addIndicator(0);
        mViewPager.addOnPageChangeListener(onPageChangeListener);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCurrentPage - 1);
                mCounter -= 1;
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCurrentPage + 1);
                mCounter += 1;


                if (mCounter == 3) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    mCounter = 0;
                }
            }
        });
    }

    public void addIndicator(int position) {
        mDotsTextView = new TextView[3];
        mLinearLayout.removeAllViews();

        for (int i = 0; i < mDotsTextView.length; i++) {
            mDotsTextView[i] = new TextView(this);
            mDotsTextView[i].setText(Html.fromHtml("&#8226;"));
            mDotsTextView[i].setTextSize(35);
            mDotsTextView[i].setTextColor(getResources().getColor(R.color.white));
            mLinearLayout.addView(mDotsTextView[i]);
        }

        if (mDotsTextView.length > 0) {
            mDotsTextView[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addIndicator(position);
            mCurrentPage = position;

            if (position == 0) {
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(false);
                mBackButton.setVisibility(View.INVISIBLE);
                mNextButton.setText(getResources().getText(R.string.next));
                mBackButton.setText("");
            } else if (position == mDotsTextView.length - 1) {
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);
                mNextButton.setText(R.string.finish);
                mBackButton.setText(R.string.back);
            } else {
                mNextButton.setEnabled(true);
                mBackButton.setEnabled(true);
                mBackButton.setVisibility(View.VISIBLE);
                mNextButton.setText(R.string.next);
                mBackButton.setText(R.string.back);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
