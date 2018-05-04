package udacity.designvilla;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.splashscreenjava.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import udacity.designvilla.adapter_view.Adapter;
import udacity.designvilla.adapter_view.TemplateHolder;

public class HomeFragment extends Fragment {

    private static final String TAG = "FB";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TemplateHolder> templateHolders;
    private FirebaseAuth fireBaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Boolean isFirstPageFirstLoad = true;
    private DocumentSnapshot lastVisible;

    //TODO: Implement main logic for firebase realtime database

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation, null);
        templateHolders = new ArrayList<>();

        //RecyclerView
        mRecyclerView = rootView.findViewById(R.id.template_recycler_view_fragment);

        fireBaseAuth = FirebaseAuth.getInstance();

        //Use a Staggered Layout Manager
        mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set Adapter
        mAdapter = new Adapter(templateHolders);
        mRecyclerView.setAdapter(mAdapter);

        if (fireBaseAuth.getCurrentUser() != null) {
            firebaseFirestore = FirebaseFirestore.getInstance();

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {
                        loadMorePost();
                    }
                }
            });
            Query firstQuery = firebaseFirestore.collection("templates").limit(3);
            firstQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (!queryDocumentSnapshots.isEmpty()) {
                        if (isFirstPageFirstLoad) {
                            lastVisible = queryDocumentSnapshots.getDocuments().get(queryDocumentSnapshots.size() - 1);
                            templateHolders.clear();
                        }

                        for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                            if (doc.getType() == DocumentChange.Type.ADDED) {
                                String image_url = doc.getDocument().getId();
                                TemplateHolder template = doc.getDocument().toObject(TemplateHolder.class).withId(image_url);
                                Log.v("template", String.valueOf(template.getImage_url()));

                                if (isFirstPageFirstLoad) {
                                    templateHolders.add(template);
                                } else {
                                    templateHolders.add(0, template);
                                }

                                mAdapter.notifyDataSetChanged();
                            }
                        }

                        isFirstPageFirstLoad = false;
                    }
                }
            });
        }
        return rootView;
    }

    public void loadMorePost() {
        if (fireBaseAuth.getCurrentUser() != null) {

            Query nextQuery = firebaseFirestore.collection("templates")
                    .startAfter(lastVisible)
                    .limit(3);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
