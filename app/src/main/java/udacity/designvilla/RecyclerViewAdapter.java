package udacity.designvilla;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.splashscreenjava.R;

import java.util.List;

/**
 * Created by NITISH KUMAR on 13-05-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
    private List<ItemObjects> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemObjects> itemList) {
        this.itemList = itemList;
        this.context = context;

    }


    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterllst, null);
        AdapterViewHolder rcv = new AdapterViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getName());
        holder.photo.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
