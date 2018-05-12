package udacity.designvilla;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.splashscreenjava.R;
import com.google.firebase.database.FirebaseDatabase;

import udacity.designvilla.Util.DesignModel;

import java.util.ArrayList;
import java.util.List;


public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ViewHolder> {

    private final List<DesignModel> mValues;
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<String> itemsUID;

    public FirebaseAdapter(List<DesignModel> items, ArrayList<String> itemsUID, Context context) {
        mValues = items;
        this.context = context;
        this.itemsUID = itemsUID;
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
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.mItem.getLikes() + 1;
                holder.imageButton.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_like));
                firebaseDatabase.getReference().child("database").child(holder.mUID).child("likes").setValue(n);
            }
        });
        Glide.with(context).load(Uri.parse(holder.mItem.getImage_url())).into(holder.mDesign);



        holder.mDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DesignDetails.class);
                intent.putExtra("author",holder.mItem.getAuthor());
                intent.putExtra("title",holder.mItem.getTitle());
                intent.putExtra("image_url",holder.mItem.getImage_url());
                intent.putExtra("xml",holder.mItem.getXml());
                intent.putExtra("likes",holder.mItem.getLikes());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
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
