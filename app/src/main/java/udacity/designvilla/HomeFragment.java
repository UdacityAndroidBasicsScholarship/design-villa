package udacity.designvilla;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.splashscreenjava.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import udacity.designvilla.Util.DesignModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {



    private FirebaseDatabase firebaseDatabase;
    private ArrayList<DesignModel> items;
    private ArrayList<String> itemsUID;
    private FirebaseAdapter mAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        items = new ArrayList<>();
        itemsUID = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
        mAdapter = new FirebaseAdapter(items, itemsUID,getActivity().getApplicationContext());
        recyclerView.setAdapter(mAdapter);
        getDesignItems();
        return view;
    }

    public void getDesignItems(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    itemsUID.add((snapshot.getKey()));
                    items.add(snapshot.getValue(DesignModel.class));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebaseDatabase.getReference().child("database").addListenerForSingleValueEvent(valueEventListener);
    }
}
