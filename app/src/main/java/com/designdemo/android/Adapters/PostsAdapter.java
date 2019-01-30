package com.designdemo.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MVH>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public PostsAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public PostsAdapter.MVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_lauout,parent,false);
        return new MVH(itemview);
    }

    @Override
    public void onBindViewHolder(MVH holder, int position)
    {
        HashMap<String,Object> map=list.get(position);
        String post_title = map.get("post_title").toString();
        String post_cat = map.get("post_cat").toString();
        String post_desc = map.get("post_desc").toString();
        String post_url = map.get("post_url").toString();
        holder.tv_post_nm.setText(post_title);
        holder.tv_post_desc.setText(post_desc);
        holder.tv_post_cat.setText(post_cat);
        Glide.with(holder.img_post.getContext()).load(post_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_post);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MVH extends RecyclerView.ViewHolder
    {
        ImageView img_post;
        TextView tv_post_nm,tv_post_cat,tv_post_desc;
        public MVH(View itemView)
        {
            super(itemView);
            tv_post_nm = itemView.findViewById(R.id.tv_post_nm);
            img_post = itemView.findViewById(R.id.img_post);
            tv_post_cat = itemView.findViewById(R.id.tv_post_cat);
            tv_post_desc = itemView.findViewById(R.id.tv_post_desc);
        }
    }
}
