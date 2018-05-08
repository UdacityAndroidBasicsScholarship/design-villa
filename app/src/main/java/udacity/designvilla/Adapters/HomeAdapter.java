package udacity.designvilla.Adapters;

import android.content.Context;
import android.util.Log;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import udacity.designvilla.Model.UserId;
import udacity.designvilla.ViewHolders.HomeHolder;

public class HomeAdapter extends FirebaseRecyclerAdapter<UserId, HomeHolder> {

    private static final String TAG = HomeAdapter.class.getSimpleName();

    public HomeAdapter(Class<UserId> modelClass, int modelLayout, Class<HomeHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }
    @Override
    protected void populateViewHolder(HomeHolder viewHolder, UserId model, int position) {
        viewHolder.bindImages(model);
    }
}
