package udacity.designvilla;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.splashscreenjava.R;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.util.Objects;

public class FavouritesActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ArcNavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        mDrawerLayout = findViewById(R.id.home_navigation_layout);
        mNavigationView = findViewById(R.id.home_navigation_view);

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.drawer_icon);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // set item as selected to persist highlight
                item.setChecked(true);
                //close the navigation bar when clicked
                mDrawerLayout.closeDrawers();

                int id = item.getItemId();

                if (id == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                } else if (id == R.id.favourites) {
                    Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
                    startActivity(intent);

                } else if (id == R.id.help) {
                    Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.contact) {
                    Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.github) {
                    Toast.makeText(getApplicationContext(), "GitHub", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.about) {

                    openDialog();


                } else if (id == R.id.rate) {
                    Toast.makeText(getApplicationContext(), "Rate", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.exit:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit_options,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void openDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.about);
        alertDialog.setMessage(R.string.info);


        alertDialog.setCancelable(true);
        alertDialog.show();
    }



}

