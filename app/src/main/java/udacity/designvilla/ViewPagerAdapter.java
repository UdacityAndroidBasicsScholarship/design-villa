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

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //constructor for ViewPagerAdapter
    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    /**
     * @param mSlideIamges :  integer array to store images for each view on the viewpager
     * @param mSlideDescip : string array to store the description of each view on the viewpager
     */
    public int[] mSlideImages = {R.drawable.first_logo, R.drawable.second_logo, R.drawable.third_logo};
    public String[] mSlideDescrip = {"This is the first slide", "This is the second slide", "This is the third slide"};


    @Override
    public int getCount() {
        return mSlideDescrip.length;
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

        imageView.setImageResource(mSlideImages[position]);
        textView.setText(mSlideDescrip[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
