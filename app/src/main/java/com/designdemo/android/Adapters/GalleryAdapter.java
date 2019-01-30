package com.designdemo.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.designdemo.android.BusinessDetailsActivity;
import com.designdemo.android.HomeActivity;
import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;
    ArrayList<String> images = new ArrayList<>();

    public GalleryAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, final int position)
    {
        HashMap<String,Object> map=list.get(position);
        String gallery_dt = map.get("img_dt").toString();
        final String gallery_nm = map.get("img_nm").toString();
        images.add(gallery_nm);
        String gallery_img_url = map.get("img_url").toString();
        holder.tv_gallery_dt.setText(gallery_dt);
        holder.tv_gallery_nm.setText(gallery_nm);
        Glide.with(holder.img_gallery.getContext()).load(gallery_img_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_gallery);
        holder.img_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusinessDetailsActivity.openImages(images.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_gallery;
        TextView tv_gallery_dt,tv_gallery_nm;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            img_gallery = itemView.findViewById(R.id.img_gallery);
            tv_gallery_dt = itemView.findViewById(R.id.tv_gallery_dt);
            tv_gallery_nm = itemView.findViewById(R.id.tv_gallery_nm);
        }
    }
}
