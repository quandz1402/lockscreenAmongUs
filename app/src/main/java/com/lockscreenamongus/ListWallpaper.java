package com.lockscreenamongus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.lockscreenamongus.R;
import com.lockscreenamongus.utils.RecyclerItemClickListener;
import com.lockscreenamongus.utils.Wallpaper;
import com.lockscreenamongus.utils.wallpaperAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListWallpaper extends AppCompatActivity {
    private List<Wallpaper> wallpapersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private wallpaperAdapter mAdapter;
    private ImageView back;
    private com.facebook.ads.AdView adView;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wallpaper);
        recyclerView =findViewById(R.id.rcw_icream1);


        back =findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter = new wallpaperAdapter(wallpapersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListWallpaper.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(ListWallpaper.this, 2, GridLayout.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        loadBannerFB();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListWallpaper.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent myIntent = new Intent(ListWallpaper.this, set_wallpaper.class);
                        myIntent.putExtra("index", wallpapersList.get(position).getIndex());
                        startActivity(myIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
    private void prepareMovieData() {
        Wallpaper wallpaper = new Wallpaper(R.mipmap.a1s,R.mipmap.a1);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a2s,R.mipmap.a2);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a3s,R.mipmap.a3);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a4s,R.mipmap.a4);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a5s,R.mipmap.a5);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a6s,R.mipmap.a6);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a7s,R.mipmap.a7);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a8s,R.mipmap.a8);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a9s,R.mipmap.a9);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a10s,R.mipmap.a10);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a11s,R.mipmap.a11);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a12s,R.mipmap.a12);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a13s,R.mipmap.a13);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a14s,R.mipmap.a14);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a15s,R.mipmap.a15);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a16s,R.mipmap.a16);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a17s,R.mipmap.a17);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a18s,R.mipmap.a18);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a19s,R.mipmap.a19);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a20s,R.mipmap.a20);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a21s,R.mipmap.a21);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a22s,R.mipmap.a22);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a23s,R.mipmap.a23);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a24s,R.mipmap.a24);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a25s,R.mipmap.a25);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a26s,R.mipmap.a26);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a27s,R.mipmap.a27);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a28s,R.mipmap.a28);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a29s,R.mipmap.a29);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a30s,R.mipmap.a30);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a31s,R.mipmap.a31);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a32s,R.mipmap.a32);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a33s,R.mipmap.a33);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a34s,R.mipmap.a34);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a35s,R.mipmap.a35);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a36s,R.mipmap.a36);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a37s,R.mipmap.a37);
        wallpapersList.add(wallpaper);
        wallpaper = new Wallpaper(R.mipmap.a38s,R.mipmap.a38);
        wallpapersList.add(wallpaper);

        mAdapter.notifyDataSetChanged();
    }
    public void loadBannerGG() {
        final View adContainer = findViewById(R.id.adMobView);
        final AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(MainActivity.BANNER_ID);
        ((LinearLayout) adContainer).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                adContainer.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        Random r = new Random();
        int ads = r.nextInt(100);

        if (ads >= MainActivity.PERCENT_SHOW_BANNER_AD) {
            mAdView.destroy();
            mAdView.setVisibility(View.GONE);
        }
    }

    public void loadBannerFB() {
        final View adContainer = findViewById(R.id.adMobView);
        AudienceNetworkAds.initialize(this);
        adView = new com.facebook.ads.AdView(this, MainActivity.BANNER_ID_FB, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((LinearLayout) adContainer).addView(adView);

// Request an ad
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                loadBannerGG();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                adContainer.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

        Random r = new Random();
        int ads = r.nextInt(100);

        if (ads >= MainActivity.PERCENT_SHOW_BANNER_AD) {
            adView.destroy();
            adView.setVisibility(View.GONE);
        }

    }
}