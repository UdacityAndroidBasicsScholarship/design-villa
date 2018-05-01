package udacity.designvilla;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splashscreenjava.R;

import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    private List<GridItem> gridItemList;

    public HomeGridAdapter(List<GridItem> list) {
        gridItemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(gridItemList.get(position).getmImageView());
        holder.textView.setText(gridItemList.get(position).getmTextView());
        holder.more_icon.setImageResource(R.drawable.ic_more_vert_black_24dp);
    }

    @Override
    public int getItemCount() {
        return gridItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageView more_icon;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            more_icon = itemView.findViewById(R.id.more_icon);
        }
    }
}
