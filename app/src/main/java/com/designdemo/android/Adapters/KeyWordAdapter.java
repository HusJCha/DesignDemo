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

public class KeyWordAdapter extends RecyclerView.Adapter<KeyWordAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public KeyWordAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public KeyWordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(KeyWordAdapter.MyViewHolder holder, int position)
    {
        HashMap<String,Object> map = list.get(position);
        Log.e("75236984123658",map.toString());
        String key_id = map.get("key_id").toString();
        String key_nm = map.get("key_nm").toString();
        holder.tv_key_nm.setText(key_nm);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_key_nm;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv_key_nm = itemView.findViewById(R.id.tv_key_nm);
        }
    }
}
