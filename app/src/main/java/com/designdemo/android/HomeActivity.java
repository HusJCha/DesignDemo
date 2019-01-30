package com.designdemo.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.designdemo.android.Adapters.CategoryAdapter;
import com.designdemo.android.app.AppController;

import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
{
    RecyclerView rec_cat;
    static Context con;
    private static String urlJsonObj = "http://rajeshwersoftsolution.com/octopus_biz/public/api/user_listing";
    private static String TAG = "=============>>>>>>>";
    private static String img_url ="http://rajeshwersoftsolution.com/nearest_room_finder/public/uploads/category/";
    Button btn_sort;
    SearchView sv_search;
    RecyclerView.LayoutManager layoutManager;
    CategoryAdapter adapter;
    ArrayList<String> cats;
    ArrayList<HashMap<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findviewbyid();
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rec_cat.setLayoutManager(layoutManager);
        con=this;
        rec_cat.setItemAnimator(new DefaultItemAnimator());
        cats = new ArrayList<>();
        list = new ArrayList<>();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.e(TAG, response.toString());
                try
                {
                    img_url = "http://rajeshwersoftsolution.com/octopus_biz/public/";
                    JSONObject object = new JSONObject(response);
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0 ; i<data.length();i++)
                    {
                        JSONObject record = data.getJSONObject(i);
                        String nm = record.getString("name");
                        String email = record.getString("email");
                        String mobile = record.getString("mobile_no");
                        String logo = record.getString("logo");
                        String description = record.getString("description");
                        img_url = img_url + logo;
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("name",nm);
                        map.put("email",email);
                        map.put("mobile",mobile);
                        map.put("logo",img_url);
                        map.put("description",description);
                        list.add(map);
                    }
                    adapter = new CategoryAdapter(getApplicationContext(),list);
                    rec_cat.setAdapter(adapter);
                }

                catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error  : ",error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String > params = new HashMap<String, String >();
                params.put("Authorization","MzVmMjJmOTQt");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String > params = new HashMap<String, String >();
                params.put("user_id","1");
                return params;
            }
        };
            AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void findviewbyid()
    {
        rec_cat = findViewById(R.id.rec_cat);
        btn_sort = findViewById(R.id.btn_sort);
        sv_search = findViewById(R.id.sv_search);
    }

    public static void openBusinessDetail(String name)
    {
        Intent i = new Intent(con,BusinessDetailsActivity.class);
        i.putExtra("com_nm",name);
        con.startActivity(i);
    }

}
