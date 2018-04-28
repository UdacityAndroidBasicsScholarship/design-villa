package udacity.designvilla.adapter_view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.splashscreenjava.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<TemplateHolder> templateHolderList;
    private Context mContext;

    public Adapter(List<TemplateHolder> templateHolders, Context context) {
        templateHolderList = templateHolders;
        mContext = context;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        //Create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_item_layout, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.templateImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.templateImageView.setImageDrawable(
                ContextCompat.getDrawable(mContext,
                        templateHolderList.get(position).getTemplateResourceId()));
        int cellHeight = holder.templateImageView.getHeight();
        Log.v("Cell Height", String.valueOf(cellHeight));
    }

    //Return the size of dataset (invoked by layout manager
    @Override
    public int getItemCount() {
        return templateHolderList.size();
    }

    //Find all Views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView templateImageView;

        public ViewHolder(View view) {
            super(view);
            templateImageView = view.findViewById(R.id.template_image_view);
        }
    }
}
