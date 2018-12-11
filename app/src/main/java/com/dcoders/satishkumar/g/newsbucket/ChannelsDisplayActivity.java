package com.dcoders.satishkumar.g.newsbucket;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dcoders.satishkumar.g.newsbucket.fragmentClasses.FavFragment;
import com.dcoders.satishkumar.g.newsbucket.fragmentClasses.NewsFragment;

public class ChannelsDisplayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    public static final String INTERNETUNAVAILABLE = "Internet Unavailable";
    public static final String CHANNELKEY = "Channel_key";
    public static final String NAME="NAME";
    public static final String PHOTO="PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String accoutnNAme = getIntent().getStringExtra(NAME);
        String accoutPhoto = getIntent().getStringExtra(PHOTO);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView textView = header.findViewById(R.id.username);
        textView.append(accoutnNAme);
        ImageView imageView = header.findViewById(R.id.account_image);
        Glide.with(this).load(accoutPhoto).into(imageView);
        Toast.makeText(this, accoutnNAme, Toast.LENGTH_SHORT).show();

    }

    public void something(){
        String some="hi guys how R YOU ";
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.channels_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.alarm) {
            Intent intent = new Intent(this, AlarmActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        int id = item.getItemId();
        if (id == R.id.abc_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.abc_news_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.abc_news));

        } else if (id == R.id.bbc_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.bbc_news_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.bbc_news));


        } else if (id == R.id.bbc_sports && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.bbc_sports_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.bbc_sports));


        } else if (id == R.id.cbc_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.cbc_news_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.cbc_news));

        } else if (id == R.id.cnn_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.cnn_news_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.cnn_news));

        } else if (id == R.id.entertainment_weekly && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.entertainment_weekly_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.entertainment_weekly));

        } else if (id == R.id.espn_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.espn_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.espn));

        } else if (id == R.id.financial_times) {
            String key = getResources().getString(R.string.financial_times_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.financial_times));

        } else if (id == R.id.google_news_india && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.google_news_india_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.google_news_india));

        } else if (id == R.id.hacker_news && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.hacker_news_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.hacker_news));

        } else if (id == R.id.national_geographic && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.national_geographic_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.national_geographic));
        } else if (id == R.id.the_hindu && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.the_hindu_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.the_hindu));

        } else if (id == R.id.the_newyork_times && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.the_newyork_times_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.the_newyork_times));

        } else if (id == R.id.the_times_of_india && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.the_times_of_india_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.the_times_of_india));

        } else if (id == R.id.time && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.time_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.time));

        } else if (id == R.id.business_insider && checkInternetAvaiability()) {
            String key = getResources().getString(R.string.business_insider_key);
            fragment = new NewsFragment();
            bundle.putString(CHANNELKEY, key);
            fragment.setArguments(bundle);
            setTitle(getResources().getString(R.string.business_insider_uk));
        } else if (id == R.id.favorites) {
            setTitle(getResources().getString(R.string.favorites));
            fragment = new FavFragment();
        } else {
            showMessage();
        }
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.article_container, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkInternetAvaiability() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public void showMessage() {
        new AlertDialog.Builder(this)
                .setTitle(INTERNETUNAVAILABLE)
                .setMessage("Sorry! Can't load the Data")
                .setPositiveButton("Yes", null)
                .setCancelable(true)
                .create();
    }
}
