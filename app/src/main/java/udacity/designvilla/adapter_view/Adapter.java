package udacity.designvilla.adapter_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.splashscreenjava.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public Context context;
    private List<TemplateHolder> templateHolderList;
    private ImageView mtemplateImageView;
    private View mView;

    public Adapter(List<TemplateHolder> templateHolders) {
        templateHolderList = templateHolders;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        //Create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_item_layout, parent, false);
        context = parent.getContext();

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.templateImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //Get URL
        String image_url = templateHolderList.get(position).getImage_url();
        holder.setTemplateImage(image_url);
    }

    //Return the size of dataset (invoked by layout manager
    @Override
    public int getItemCount() {
        return templateHolderList.size();
    }

    //Find all Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView templateImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            templateImageView = view.findViewById(R.id.template_image_view);
        }

        public void setTemplateImage(String image_url) {
            mtemplateImageView = mView.findViewById(R.id.template_image_view);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.placeholder);
            requestOptions.centerCrop();

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(image_url).into(mtemplateImageView);
        }
    }
}
