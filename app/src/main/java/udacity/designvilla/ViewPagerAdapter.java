package udacity.designvilla;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

/**
 * Created by Lianding on 4/18/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

    /**
     * @param mSlideImages :  integer array to store images for each view on the viewpager
     * @param mSlideDesc : string array to store the description of each view on the viewpager
     * @param mSlideHeader : string array to store the header of each view
     */
    public int[] mSlideImages = {R.drawable.villa3, R.drawable.showcase, R.drawable.justjava, R.drawable.quiz, R.drawable.villa1};
    public String[] mSlideHeader = {"welcome to design villa !", "Showcase your work here..!!  ", "Just java apps", "Quiz app", "get started.. !!"};
    public String[] mSlideDesc = {" ", "Place your dream projects here....!!", "Enjoy sip of favourite coffee, with your loved one's along with just java app....!", "Let's explore how sharp is your mind, play and have results @ your gmail...!!","Pin your apps here.\nTo know more about this app and it's developer, click next. "};
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //constructor for ViewPagerAdapter
    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSlideHeader.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.sliding_layout, container, false);

        ImageView imageView = view.findViewById(R.id.slide_image_view);
        TextView textView = view.findViewById(R.id.slide_text_view);
        TextView textView1 = view.findViewById(R.id.slide_text_view_desc);

        imageView.setImageResource(mSlideImages[position]);
        textView.setText(mSlideHeader[position]);
        textView1.setText(mSlideDesc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
