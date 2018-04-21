package udacity.designvilla;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

/**
 * Created by Lakshmi on 21-Apr-18.
 */

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    //list of images
    public int[] lst_images = {
            R.drawable.cake,
            R.drawable.coffeee,
            R.drawable.football,
            R.drawable.quiz,
            R.drawable.ring,
    };
    //list of titles
    public String[] lst_titles ={
            "Happy Birthday Apps",
            "Just Java Apps",
            "Court Counter Apps",
            "Quiz Apps",
            "And The List goes on "
    };
    //list of descriptions
    public String[] lst_description= {
            "The first step towards app building ..!",
            "Want a cup of coffee ? " +
                    "Order from -\"Our COFFEE APPS\"",
            "Playing games along with Java code ..!",
            "Testing our android skills and general knowelge ...!",
            " ..Because is no end for learning !"
    };
    //list of background colors
    public int[] lst_backgroundcolor = {
            Color.rgb(255,123,121),
            Color.rgb(175,94,39),
            Color.rgb(250,78,118),
            Color.rgb(201,135,251),
            Color.rgb(24,128,250)
    };


    public SlideAdapter(Context context){
        this.context = context;
    }

    //returns the no of slides
    @Override
    public int getCount() {
        return lst_titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout)view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)view.findViewById(R.id.slideimg);
        TextView txttitle = (TextView)view.findViewById(R.id.txttitle);
        TextView description = (TextView)view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_titles[position]);
        description.setText(lst_description[position]);
        container.addView(view);

        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }




}
