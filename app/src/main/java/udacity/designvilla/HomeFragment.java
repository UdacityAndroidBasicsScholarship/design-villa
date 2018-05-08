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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import udacity.designvilla.Adapters.HomeAdapter;
import udacity.designvilla.Model.UserId;
import udacity.designvilla.ViewHolders.HomeHolder;

public class HomeFragment extends Fragment {

    //TODO: Implement main logic for firebase realtime database

    private RecyclerView recyclerView;
    private DatabaseReference mdbReference;
    private HomeAdapter mFirebaseAdapter;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_navigation, null);
        setUpDbConnection();
        recyclerView = (RecyclerView)view.findViewById(R.id.home_list);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpDbConnection(){
        FirebaseApp.initializeApp(getActivity());
        mdbReference= FirebaseDatabase.getInstance()
                .getReference("database");
    }

    private void setUpFirebaseAdapter() {

        mFirebaseAdapter = new HomeAdapter(UserId.class,R.layout.image_list_item,HomeHolder.class,
                mdbReference,getActivity());
        /*mFirebaseAdapter = new FirebaseRecyclerAdapter<UserId, FirebaseHomeViewHolder>
                (UserId.class, R.layout.image_list_item, FirebaseHomeViewHolder.class,
                        mdbReference) {

            @Override
            protected void populateViewHolder(FirebaseHomeViewHolder viewHolder,
                                              UserId model, int position) {
                viewHolder.bindImages(model);
            }
        };*/
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
