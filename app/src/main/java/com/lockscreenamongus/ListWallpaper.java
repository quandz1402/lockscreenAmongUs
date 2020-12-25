package com.lockscreenamongus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.lockscreenamongus.R;
import com.lockscreenamongus.utils.RecyclerItemClickListener;
import com.lockscreenamongus.utils.Wallpaper;
import com.lockscreenamongus.utils.wallpaperAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListWallpaper extends AppCompatActivity {
    private List<Wallpaper> wallpapersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private wallpaperAdapter mAdapter;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wallpaper);
        recyclerView =findViewById(R.id.rcw_icream1);



        mAdapter = new wallpaperAdapter(wallpapersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListWallpaper.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(ListWallpaper.this, 2, GridLayout.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();

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
}