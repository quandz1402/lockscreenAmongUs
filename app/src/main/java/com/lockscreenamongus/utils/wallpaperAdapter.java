package com.lockscreenamongus.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;


import com.lockscreenamongus.MainActivity;
import com.lockscreenamongus.R;

import java.util.List;

public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.MyViewHolder> {

    private List<Wallpaper> wallpapersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image =  view.findViewById(R.id.image);

        }
    }


    public wallpaperAdapter(List<Wallpaper> wallpapersList) {
        this.wallpapersList = wallpapersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rcv, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Wallpaper wallpaper = wallpapersList.get(position);
        holder.image.getLayoutParams().width = (int) (MainActivity.width * 0.4);
        holder.image.getLayoutParams().height = (int) (MainActivity.height * 0.4);
        holder.image.requestLayout();
//        if(position % 2 == 0){
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(1, 0, 0, 0);
//            holder.image.setLayoutParams(lp);
//        }
        holder.image.setImageResource(wallpaper.getUrl());
    }

    @Override
    public int getItemCount() {
        return wallpapersList.size();
    }
}