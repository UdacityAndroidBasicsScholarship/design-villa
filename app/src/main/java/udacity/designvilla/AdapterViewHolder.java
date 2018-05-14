package udacity.designvilla;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.splashscreenjava.R;

/**
 * Created by NITISH KUMAR on 13-05-2018.
 */

class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public ImageView photo;

    public AdapterViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = (TextView) itemView.findViewById(R.id.name);
        photo = (ImageView) itemView.findViewById(R.id.photo);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
    }
}
