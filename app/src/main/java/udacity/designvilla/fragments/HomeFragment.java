package udacity.designvilla.fragments;

import android.app.Fragment;
import android.os.Bundle;
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
import java.util.Objects;

import udacity.designvilla.R;
import udacity.designvilla.model.DesignModel;
import udacity.designvilla.util.FirebaseAdapter;

public class HomeFragment extends Fragment {


    private FirebaseDatabase firebaseDatabase;
    private ArrayList<DesignModel> items;
    private ArrayList<String> itemsUID;
    private FirebaseAdapter mAdapter;
    private RecyclerView recyclerView;
    private String userID;
    private ArrayList<String> favorites;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        userID = Objects.requireNonNull(FirebaseAuth.getInstance()).getUid();
        items = new ArrayList<>();
        itemsUID = new ArrayList<>();
        favorites = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.list);
        mAdapter = new FirebaseAdapter(items, itemsUID, favorites, getActivity().getApplicationContext());
        getDesignItems();
        getFavorites();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(mAdapter);
        return recyclerView;
    }

    public void getDesignItems() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    itemsUID.add((snapshot.getKey()));
                    items.add(snapshot.getValue(DesignModel.class));
                }
                recyclerView.getRecycledViewPool().clear();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebaseDatabase.getReference().child("database").addListenerForSingleValueEvent(valueEventListener);
    }

    public void getFavorites() {
        ValueEventListener favouritesValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    favorites.add(Objects.requireNonNull(snapshot.getValue()).toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        firebaseDatabase.getReference().child("favourites").child(userID)
                .addListenerForSingleValueEvent(favouritesValueEventListener);
    }
}