package com.designdemo.android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.designdemo.android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public TrainingAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public TrainingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(TrainingAdapter.MyViewHolder holder, int position)
    {
        HashMap<String,Object> map = list.get(position);
        String training_id = map.get("training_id").toString();
        String training_nm = map.get("training_nm").toString();
        holder.tv_training_nm.setText(training_nm);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_training_nm;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv_training_nm = itemView.findViewById(R.id.tv_training_nm);
        }
    }
}
