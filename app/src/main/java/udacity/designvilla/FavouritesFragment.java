package udacity.designvilla;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import udacity.designvilla.Util.DesignModel;

public class FavouritesFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private ArrayList<DesignModel> items;
    private ArrayList<String> itemsUID;
    private FirebaseAdapter mAdapter;
    private FirebaseAuth firebaseAuth;
    private String userID;

    //TODO:Implement Favourites for firebsae realtime database

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        items = new ArrayList<>();
        itemsUID = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mAdapter = new FirebaseAdapter(items, itemsUID,getActivity().getApplicationContext());
        recyclerView.setAdapter(mAdapter);
        getDesignItems();
        return view;
    }

    public void getDesignItems(){
        final ValueEventListener databaseValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    for(String key:itemsUID) {
                        if(snapshot.getKey().equals(key))
                        items.add(snapshot.getValue(DesignModel.class));
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        ValueEventListener favouritesValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    itemsUID.add(snapshot.getValue().toString());
                }
                firebaseDatabase.getReference().child("database").addListenerForSingleValueEvent(databaseValueEventListener);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebaseDatabase.getReference().child("favourites").child(userID).addListenerForSingleValueEvent(favouritesValueEventListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
