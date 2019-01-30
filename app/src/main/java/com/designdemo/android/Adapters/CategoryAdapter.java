package com.designdemo.android.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.designdemo.android.HomeActivity;
import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public CategoryAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_category,parent,false);
        return new MyViewHolder(itemview);
    }

    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position)
    {
        HashMap<String,Object> map =new HashMap<>();
        map = list.get(position);
        final String str_com_nm = map.get("name").toString();
        String str_email = map.get("email").toString();
        String str_mobile = map.get("mobile").toString();
        String str_logo = map.get("logo").toString();
        String str_add = map.get("description").toString();
        holder.tv_nm.setText(str_com_nm);
        holder.tv_email.setText(str_email);
        holder.tv_mobile.setText(str_mobile);
        holder.tv_add.setText(str_add);
        Glide.with(con).load(str_logo).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_cat);
        holder.img_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.openBusinessDetail(str_com_nm);
            }
        });
        holder.cv_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.openBusinessDetail(str_com_nm);
            }
        });
//        holder.ll_cat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.openBusinessDetail(str_com_nm);
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout ll_cat;
        CardView cv_cat;
        ImageView img_cat;
        TextView tv_nm,tv_email,tv_mobile,tv_add;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            img_cat = itemView.findViewById(R.id.img_cat);
            tv_nm = itemView.findViewById(R.id.tv_com_nm);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_add = itemView.findViewById(R.id.tv_add);
            cv_cat = itemView.findViewById(R.id.card_view_cat);
            ll_cat = itemView.findViewById(R.id.ll_cat);
        }
    }
}
