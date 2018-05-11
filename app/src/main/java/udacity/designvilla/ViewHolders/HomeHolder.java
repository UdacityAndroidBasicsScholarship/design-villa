package udacity.designvilla.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;
import com.squareup.picasso.Picasso;

import udacity.designvilla.Adapters.HomeAdapter;
import udacity.designvilla.Model.UserId;

public class HomeHolder extends RecyclerView.ViewHolder {
    private static final String TAG = HomeHolder.class.getSimpleName();
    public TextView recipeName;
    public ImageView recipeImage;
    private View mView;

    public HomeHolder(View itemView) {
        super(itemView);
        mView = itemView;
        recipeName = (TextView)itemView.findViewById(R.id.image_name);
        recipeImage = (ImageView)itemView.findViewById(R.id.image);
    }

    public void bindImages(UserId model) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.image);
        TextView imagename = (TextView) mView.findViewById(R.id.image_name);
        TextView image_title = (TextView) mView.findViewById(R.id.image_title);

        Picasso.get()
                .load(model.getImage_url())
                .into(imageView);

        imagename.setText(model.getAuthor());
        image_title.setText(model.getTitle());
    }
}
