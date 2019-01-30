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

public class BusinessHoursAdapter extends RecyclerView.Adapter<BusinessHoursAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;

    public BusinessHoursAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public BusinessHoursAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_hours_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(BusinessHoursAdapter.MyViewHolder holder, int position)
    {
        HashMap map = list.get(position);
        String str_bh_day = map.get("bh_day").toString();
        Integer bh_day = Integer.parseInt(str_bh_day);
        String bh_st = map.get("bh_st").toString();
        String bh_et = map.get("bh_et").toString();
        String day ="";
        switch (bh_day) {
            case 1 :
            day = "Monday";
            break;
            case 2 :
            day = "Tuesday";
            break;
            case 3 :
            day = "Wednesday";
            break;
            case 4 :
            day = "Thursday";
            break;
            case 5 :
            day = "Friday";
            break;
            case 6 :
            day = "Saturday";
            break;
            case 7 :
            day = "Sunday";
            break;
            default:
                day = "No One";
        }
        holder.tv_bh_day.setText(day);
        holder.tv_bh_st.setText(bh_st);
        holder.tv_bh_et.setText(bh_et);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_bh_st,tv_bh_et,tv_bh_day;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv_bh_day = itemView.findViewById(R.id.tv_day_bh);
            tv_bh_st = itemView.findViewById(R.id.tv_st_bh);
            tv_bh_et = itemView.findViewById(R.id.tv_et_bh);
        }
    }
}
