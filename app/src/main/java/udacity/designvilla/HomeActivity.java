package udacity.designvilla;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.splashscreenjava.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    List<GridItem> gridItems;
    boolean grid = true;
    private DrawerLayout mDrawerLayout;
    private ArcNavigationView mNavigationView;
    private Toolbar mToolbar;
    private FirebaseUser mUser;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mDrawerLayout = findViewById(R.id.home_navigation_layout);
        mNavigationView = findViewById(R.id.home_navigation_view);
        mRecyclerView = findViewById(R.id.recyclerView);

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.drawer_icon);

        gridItems = new ArrayList<>();
        gridItems.add(new GridItem(getDrawable(R.drawable.image1), R.string.image1));
        gridItems.add(new GridItem(getDrawable(R.drawable.image2), R.string.image2));
        gridItems.add(new GridItem(getDrawable(R.drawable.image3), R.string.image3));
        gridItems.add(new GridItem(getDrawable(R.drawable.image4), R.string.image4));
        gridItems.add(new GridItem(getDrawable(R.drawable.image5), R.string.image5));
        gridItems.add(new GridItem(getDrawable(R.drawable.image6), R.string.image6));
        gridItems.add(new GridItem(getDrawable(R.drawable.image7), R.string.image7));
        gridItems.add(new GridItem(getDrawable(R.drawable.image8), R.string.image8));
        gridItems.add(new GridItem(getDrawable(R.drawable.image9), R.string.image9));
        gridItems.add(new GridItem(getDrawable(R.drawable.image10), R.string.image10));
        gridItems.add(new GridItem(getDrawable(R.drawable.image11), R.string.image11));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new HomeGridAdapter(gridItems));


        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.main_layout, new HomeFragment());
        tx.commit();


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // set item as selected to persist highlight
                item.setChecked(true);
                Fragment fragment = new HomeFragment();

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.favourites:
                        fragment = new FavouritesFragment();
                        break;
                    case R.id.about:
                        //TODO:Design About layout layout_about
                        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                        builder.setView(R.layout.layout_about);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.sign_out:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(HomeActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.exit:
                        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);
                        alertDialog.setTitle(R.string.exit);
                        alertDialog.setMessage(R.string.are_you_sure);
                        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent closeApp = new Intent(Intent.ACTION_MAIN);
                                closeApp.addCategory(Intent.CATEGORY_HOME);
                                closeApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(closeApp);
                                finish();
                            }
                        });
                        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                        break;
                    default:
                        fragment = new HomeFragment();
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_layout, fragment);
                transaction.commit();
                //close the navigation bar when clicked
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.exit:
                android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(this);
                alertDialog.setTitle(R.string.exit);
                alertDialog.setMessage(R.string.are_you_sure);
                alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent closeApp = new Intent(Intent.ACTION_MAIN);
                        closeApp.addCategory(Intent.CATEGORY_HOME);
                        closeApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(closeApp);
                        finish();
                    }
                });
                alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.show();
                break;

            case R.id.grid_list_switch:
                if (grid) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    mRecyclerView.setAdapter(new HomeGridAdapter(gridItems));
                    Toast.makeText(this, "List View", Toast.LENGTH_SHORT).show();
                    grid = false;
                } else {
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    mRecyclerView.setAdapter(new HomeGridAdapter(gridItems));
                    Toast.makeText(this, "Grid View", Toast.LENGTH_SHORT).show();
                    grid = true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (mUser == null) {
//            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(loginIntent);
//            finish();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit_options, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
