package udacity.designvilla;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import udacity.designvilla.fragments.FavouritesFragment;
import udacity.designvilla.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    TextView username;
    TextView useremail;
    CircleImageView userPic;
    ActionBarDrawerToggle toggle;
    WebView webGit;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_home);

        mDrawerLayout = findViewById(R.id.home_navigation_layout);
        mNavigationView = findViewById(R.id.home_navigation_view);

        View navHeader = mNavigationView.getHeaderView(0);

        username = navHeader.findViewById(R.id.textView8);
        useremail = navHeader.findViewById(R.id.textView9);
        userPic = navHeader.findViewById(R.id.profile_image);

        mToolbar = findViewById(R.id.tool_bar);
        webGit = findViewById(R.id.webView);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.drawer_icon);
        }

        mNavigationView.setItemIconTintList(null);

        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        final String name;
        String email;
        Uri uriProfile;
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {

            name = mUser.getDisplayName();
            username.setText(name);

            email = mUser.getEmail();
            useremail.setText(email);

            uriProfile = mUser.getPhotoUrl();
            Glide.with(this).load(uriProfile).into(userPic);
        }

        final FragmentTransaction tx = getFragmentManager().beginTransaction();
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

                    case R.id.github:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "You will be directed to our project repository", Toast.LENGTH_SHORT).show();
                            }
                        }, 50);

                        Intent gitIntent = new Intent(HomeActivity.this, GitWebViewActivity.class);
                        startActivity(gitIntent);

                        break;

                    case R.id.rate:
                        //TODO:Implement rating  dialog
                        AlertDialog.Builder alertDialogRate = new AlertDialog.Builder(HomeActivity.this);

                        // Setting Dialog Title
                        alertDialogRate.setTitle("Notice");

                        // Setting Icon to Dialog
                        alertDialogRate.setIcon(R.drawable.ic_rate);

                        // Setting Dialog Message
                        alertDialogRate.setMessage("Wanna \u2605 us...\nSoon publishing to Play Store ");

                        // Setting Cancel Button
                        alertDialogRate.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialogRate.show();
                        break;

                    case R.id.exit:

                        //TODO:Implement exit dialog
                        final AlertDialog.Builder alertDialogExit = new AlertDialog.Builder(HomeActivity.this);

                        // Setting Dialog Title
                        alertDialogExit.setTitle("Exit");

                        // Setting Icon to Dialog
                        alertDialogExit.setIcon(R.drawable.ic_exit);

                        // Setting Dialog Message
                        alertDialogExit.setMessage("Do you really want to exit?");

                        // Setting Positive "Yes" Button
                        alertDialogExit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialogExit.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialogExit.show();
                        break;

                    case R.id.sign_out:
                        //TODO:Implement Signout dialog
                        final AlertDialog.Builder alertDialogOut = new AlertDialog.Builder(HomeActivity.this);
                        // Setting Dialog Title
                        alertDialogOut.setTitle("Signout Alert");

                        // Setting Dialog Message
                        alertDialogOut.setMessage("Do you really want to Signout?");

                        // Setting Icon to Dialog
                        alertDialogOut.setIcon(R.drawable.ic_signout);

                        // Setting Positive "Yes" Button
                        alertDialogOut.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(HomeActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialogOut.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialogOut.show();
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
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

