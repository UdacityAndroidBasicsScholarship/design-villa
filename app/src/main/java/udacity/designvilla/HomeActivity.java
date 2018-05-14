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
    CircleImageView user_pic;
    ActionBarDrawerToggle toggle;
    WebView web_git;
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
        user_pic = navHeader.findViewById(R.id.profile_image);

        mToolbar = findViewById(R.id.tool_bar);
        web_git = findViewById(R.id.webView);

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
        Uri uri_profile;
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {

            name = mUser.getDisplayName();
            username.setText(name);

            email = mUser.getEmail();
            useremail.setText(email);

            uri_profile = mUser.getPhotoUrl();
            Glide.with(this).load(uri_profile).into(user_pic);
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

                        Intent git_intent = new Intent(HomeActivity.this, GitWebView.class);
                        startActivity(git_intent);

                        break;

                    case R.id.rate:
                        //TODO:Implement rating  dialog
                        AlertDialog.Builder alertDialog_rate = new AlertDialog.Builder(HomeActivity.this);

                        // Setting Dialog Title
                        alertDialog_rate.setTitle("Notice");

                        // Setting Icon to Dialog
                        alertDialog_rate.setIcon(R.drawable.ic_rate);

                        // Setting Dialog Message
                        alertDialog_rate.setMessage("Wanna \u2605 us...\nSoon publishing to Play Store ");

                        // Setting Cancel Button
                        alertDialog_rate.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog_rate.show();
                        break;

                    case R.id.exit:

                        //TODO:Implement exit dialog
                        final AlertDialog.Builder alertDialog_exit = new AlertDialog.Builder(HomeActivity.this);

                        // Setting Dialog Title
                        alertDialog_exit.setTitle("Exit");

                        // Setting Icon to Dialog
                        alertDialog_exit.setIcon(R.drawable.ic_exit);

                        // Setting Dialog Message
                        alertDialog_exit.setMessage("Do you really want to exit?");

                        // Setting Positive "Yes" Button
                        alertDialog_exit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog_exit.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog_exit.show();
                        break;

                    case R.id.sign_out:
                        //TODO:Implement Signout dialog
                        final AlertDialog.Builder alertDialog_out = new AlertDialog.Builder(HomeActivity.this);
                        // Setting Dialog Title
                        alertDialog_out.setTitle("Signout Alert");

                        // Setting Dialog Message
                        alertDialog_out.setMessage("Do you really want to Signout?");

                        // Setting Icon to Dialog
                        alertDialog_out.setIcon(R.drawable.ic_signout);

                        // Setting Positive "Yes" Button
                        alertDialog_out.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
                        alertDialog_out.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog_out.show();
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

