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
import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public ProductsAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_images,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.MyViewHolder holder, int position)
    {
        HashMap<String,Object> map =list.get(position);
        String pro_nm = map.get("pro_nm").toString();
        Integer pro_price = (Integer)map.get("pro_price");
        String pro_img_url = map.get("pro_img_url").toString();
        holder.tv_pro_nm.setText(pro_nm);
        holder.tv_pro_price.setText("Rs. "+pro_price.toString());
        Glide.with(holder.img_pro.getContext()).load(pro_img_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_pro);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_pro;
        TextView tv_pro_nm,tv_pro_price;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            img_pro = itemView.findViewById(R.id.img_product);
            tv_pro_nm = itemView.findViewById(R.id.tv_product_nm);
            tv_pro_price = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
