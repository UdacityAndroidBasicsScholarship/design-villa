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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.splashscreenjava.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ArcNavigationView mNavigationView;
    private Toolbar mToolbar;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = findViewById(R.id.home_navigation_layout);
        mNavigationView = findViewById(R.id.home_navigation_view);
        setProfileInfoToNavHeader(); //Sets Profile Name, Email and Photo to Nav Header

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.drawer_icon);


        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.replaceable, new HomeFragment());
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
                        AlertDialog alertDialogg = new AlertDialog.Builder(HomeActivity.this).create();
                        // Setting Dialog Title
                        alertDialogg.setTitle("Design Villa");

                        // Setting Dialog Message
                        alertDialogg.setMessage("~ Version 1.0" + '\n' + "~ Developed by Codevengers" + '\n' + "~ All Rights Reserved");

                        // Setting Icon to Dialog
                        alertDialogg.setIcon(R.drawable.design_villa);

                        // Showing Alert Message
                        alertDialogg.show();
                        break;
                    case R.id.sign_out:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(HomeActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.exit:

                        //TODO:Implement exit dialog
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

                        // Setting Dialog Title
                        alertDialog.setTitle("Exit");

                        // Setting Dialog Message
                        alertDialog.setMessage("Do you really want to exit?");

                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                        break;
                    default:
                        fragment = new HomeFragment();
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.replaceable, fragment);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void setProfileInfoToNavHeader() {
        String profileEmail;
        String profileName;
        String photoUrl;
        try {
            Bundle profileBundle = getIntent().getExtras();
            profileEmail = profileBundle.getString("profileEmail");
            profileName = profileBundle.getString("profileName");
            photoUrl = profileBundle.getString("photoUrl");

            //Setting profile detail onto Views
            View drawerHeader = mNavigationView.getHeaderView(0);
            TextView profileEmailTextView = drawerHeader.findViewById(R.id.profile_email);
            profileEmailTextView.setText(profileEmail);
            TextView profileNameTextView = drawerHeader.findViewById(R.id.profile_name);
            profileNameTextView.setText(profileName);
            ImageView profileImageView = drawerHeader.findViewById(R.id.profile_image);
            Glide.with(this).load(photoUrl).into(profileImageView);

        } catch (NullPointerException e) {
            Log.d("Bundle Empty", e.toString());
        }
    }
}
