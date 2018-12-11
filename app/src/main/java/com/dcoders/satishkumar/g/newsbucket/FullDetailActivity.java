package com.dcoders.satishkumar.g.newsbucket;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dcoders.satishkumar.g.newsbucket.mainDataClasses.MyDataClass;
import com.dcoders.satishkumar.g.newsbucket.room.Favorites;
import com.dcoders.satishkumar.g.newsbucket.room.FavoritesViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FullDetailActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static final String TITLE="TITLEKEY";
    public static final String DESCRIPTION="DESCRIPTIONKEY";
    public static final String IMAGE="IMAGEKEY";
    public static final String EXTERNALURL="EXTERNALURLKEY";
    public static final String AUTHOR="AUTHORKEY";

    List<MyDataClass> list;
    ImageView imageView,favImage;
    TextView titleTv,descriptionTv;
    String externalurl;
    FavoritesViewModel viewModel;
    boolean state;
    String desc,title,image,author;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detail);
        viewModel= ViewModelProviders.of(this).get(FavoritesViewModel.class);
        list=new ArrayList<>();
        AdView mAdView = (AdView)findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        imageView=findViewById(R.id.full_detail_imageView);
        titleTv=findViewById(R.id.full_detail_title_tv);
        descriptionTv=findViewById(R.id.full_detail_description_tv);
        favImage=findViewById(R.id.full_detail_fav_image);
        final Intent intent=getIntent();
        author=intent.getStringExtra(AUTHOR);
        title=intent.getStringExtra(TITLE);
        desc=intent.getStringExtra(DESCRIPTION);
        image=intent.getStringExtra(IMAGE);
        titleTv.setText(title);
        descriptionTv.setText(desc);
        Glide.with(this).load(image).into(imageView);
        externalurl=intent.getStringExtra(EXTERNALURL);
        checkinDB(title);
        favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state)
                {
                   delete();
                   favImage.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                   state=!state;
                }
                else
                {
                    insert();
                    favImage.setImageResource(R.drawable.ic_favorite_black_24dp);
                    state=!state;
                }
            }
        });


    }

    public void showinBrowser(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(externalurl));
        startActivity(intent);
    }
    public void insert()
    {
        Favorites favorites=new Favorites();
        favorites.setAuthor(author);
        favorites.setDescription(desc);
        favorites.setImageUrl(image);
        favorites.setLink(externalurl);
        favorites.setTitle(title);
        viewModel.insert(favorites);
        Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();


    }
    public void delete(){
        Favorites favorites=new Favorites();
        favorites.setAuthor(author);
        favorites.setDescription(desc);
        favorites.setImageUrl(image);
        favorites.setLink(externalurl);
        favorites.setTitle(title);
        viewModel.delete(favorites);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();


    }
    public void checkinDB(String title)
    {
        Favorites favorites=viewModel.checkFavinDB(title);
        if (favorites==null)
        {
            favImage.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            state=false;
        }
        else
        {
            favImage.setImageResource(R.drawable.ic_favorite_black_24dp);
            state=true;
        }

    }
    public void speakNow(View view)
    {
        tts = new TextToSpeech(this, this);

    }

    @Override
    protected void onDestroy() {
        if (tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void speakOut() {
        if (desc!=null)
        {
            tts.speak(desc,TextToSpeech.QUEUE_FLUSH,null);
        }
    }
    public void stopSpeaking(View view)
    {
        tts.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return  true;
    }

    @Override
    public void onInit(int status) {
        if (status ==TextToSpeech.SUCCESS)
        {
            int result=tts.setLanguage(Locale.US);

            if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
            {
                return;
            }
            else {
                speakOut();
            }
        }
    }
}
