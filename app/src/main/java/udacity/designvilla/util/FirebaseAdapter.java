package udacity.designvilla.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import udacity.designvilla.DesignDetails;
import udacity.designvilla.R;
import udacity.designvilla.model.DesignModel;


public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ViewHolder> {

    private final List<DesignModel> mValues;
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<String> itemsUID;
    private ArrayList<String> favoritesUID;
    private boolean showFavoriteButton;

    public FirebaseAdapter(List<DesignModel> items, ArrayList<String> itemsUID,
                           ArrayList<String> favoritesUID, Context context) {
        mValues = items;
        this.context = context;
        this.itemsUID = itemsUID;
        this.favoritesUID = favoritesUID;
        showFavoriteButton = favoritesUID != null;
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mUID = itemsUID.get(position);
        holder.mAuthor.setText(mValues.get(position).getAuthor());
        holder.mContentView.setText(mValues.get(position).getTitle());

        if(showFavoriteButton) {
            if (favoritesUID.contains(holder.mUID))
                holder.imageButton.setImageDrawable(holder.imageButton.
                        getResources().getDrawable(R.drawable.ic_like));
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!favoritesUID.contains(holder.mUID))
                        addToFavorites(holder.mUID);
                    int n = holder.mItem.getLikes() + 1;
                    holder.imageButton.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_like));
                    firebaseDatabase.getReference()
                            .child("database").child(holder.mUID).child("likes").setValue(n);
                }
            });
        }else
            holder.imageButton.setVisibility(View.GONE);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.ic_error)
                .override(holder.mDesign.getWidth());

        Glide.with(context)
                .load(Uri.parse(holder.mItem.getImage_url()))
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.mDesign);


        holder.mDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DesignDetails.class);
                intent.putExtra("author", holder.mItem.getAuthor());
                intent.putExtra("title", holder.mItem.getTitle());
                intent.putExtra("image_url", holder.mItem.getImage_url());
                intent.putExtra("xml", holder.mItem.getXml());
                intent.putExtra("likes", holder.mItem.getLikes());
                intent.putExtra("layout_uid", holder.mUID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private void addToFavorites(String layoutID) {
        DatabaseReference reference = firebaseDatabase.getReference().child("favourites")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        reference.push().setValue(layoutID).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Firebase Error", Objects.requireNonNull(task.getException()).toString());
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mDesign;
        public final ImageButton imageButton;
        public final TextView mAuthor;
        public final TextView mContentView;
        public DesignModel mItem;
        public String mUID;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDesign = view.findViewById(R.id.design_image);
            mAuthor = view.findViewById(R.id.author_text_view);
            mContentView = view.findViewById(R.id.description_text_view);
            imageButton = view.findViewById(R.id.like_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
