package com.designdemo.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public CategoryDataAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public CategoryDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CategoryDataAdapter.MyViewHolder holder, int position)
    {
        HashMap<String,Object> map =list.get(position);
        String cat_nm = map.get("cat_nm").toString();
        holder.tv_cat_nm.setText(cat_nm);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_cat_id,tv_cat_nm;
        public MyViewHolder(View itemView)
        {
            super(itemView);
//            tv_cat_id = itemView.findViewById(R.id.tv_cat_id);
            tv_cat_nm = itemView.findViewById(R.id.tv_cat_nm);
        }
    }
}
