package udacity.designvilla;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.splashscreenjava.R;

import java.util.ArrayList;
import java.util.List;

import udacity.designvilla.adapter_view.Adapter;
import udacity.designvilla.adapter_view.TemplateHolder;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TemplateHolder> templateHolders;

    //TODO: Implement main logic for firebase realtime database

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation, null);
        //RecyclerView
        mRecyclerView = rootView.findViewById(R.id.template_recycler_view_fragment);

        //Use a Staggered Layout Manager
        mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set Adapter
        mAdapter = new Adapter(passDummyData());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private List<TemplateHolder> passDummyData() {
        templateHolders = new ArrayList<>();
        int n = 1;
        for (int i = 0; i < 15; i++) {
            switch (n) {
                case 1:
                    templateHolders.add(new TemplateHolder(getResources().getDrawable(R.drawable.a1)));
                    n = 2;
                    break;
                case 2:
                    templateHolders.add(new TemplateHolder(getResources().getDrawable(R.drawable.a2)));
                    n = 3;
                    break;
                case 3:
                    templateHolders.add(new TemplateHolder(getResources().getDrawable(R.drawable.a3)));
                    n = 4;
                    break;
                case 4:
                    templateHolders.add(new TemplateHolder(getResources().getDrawable(R.drawable.a4)));
                    n = 1;
                    break;
            }
        }
        return templateHolders;
    }
}
