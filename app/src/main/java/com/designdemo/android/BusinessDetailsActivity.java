package com.designdemo.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.designdemo.android.Adapters.BusinessHoursAdapter;
import com.designdemo.android.Adapters.CategoryDataAdapter;
import com.designdemo.android.Adapters.GalleryAdapter;
import com.designdemo.android.Adapters.KeyWordAdapter;
import com.designdemo.android.Adapters.PostsAdapter;
import com.designdemo.android.Adapters.ProductsAdapter;
import com.designdemo.android.Adapters.TrainingAdapter;
import com.designdemo.android.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.designdemo.android.HomeActivity.con;

public class BusinessDetailsActivity extends AppCompatActivity
{
    ImageView img_card_cat;
    TextView tv_com_nm,tv_com_email,tv_com_mobile,tv_com_add;
    String img_url = "";
    LinearLayout ll_images;
    static LinearLayout ll_whole;
    ArrayList<HashMap<String,Object>> cat_list,pro_list,gallery_list,bh_list,key_list,training_list,posts_list,images_list;
    CategoryDataAdapter categoryDataAdapter;
    ProductsAdapter productsAdapter;
    GalleryAdapter galleryAdapter;
    BusinessHoursAdapter businessHoursAdapter;
    KeyWordAdapter keyWordAdapter;
    TrainingAdapter trainingAdapter;
    PostsAdapter postsAdapter;
    RecyclerView rec_cat,rec_pro,rec_gallery,rec_bh,rec_posts,rec_key,rec_training,rec_images;
    RecyclerView.LayoutManager rec_cat_lm,rec_pro_lm,rec_gallery_lm,rec_bh_lm,rec_posts_lm,rec_key_lm,rec_training_lm,rec_images_lm;

    String TAG = "============>>>>>>>>";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        findviewbyids();
        cat_list=new ArrayList<>();
        pro_list=new ArrayList<>();
        gallery_list=new ArrayList<>();
        bh_list=new ArrayList<>();
        key_list = new ArrayList<>();
        training_list =new ArrayList<>();
        posts_list = new ArrayList<>();
        rec_cat_lm = new GridLayoutManager(getApplicationContext(),2);
        rec_pro_lm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rec_gallery_lm = new GridLayoutManager(getApplicationContext(),2);
        rec_bh_lm = new LinearLayoutManager(getApplicationContext());
        rec_key_lm = new GridLayoutManager(getApplicationContext(),2);
        rec_training_lm = new GridLayoutManager(getApplicationContext(),2);
        rec_posts_lm = new LinearLayoutManager(getApplicationContext());
        rec_cat.setLayoutManager(rec_cat_lm);
        rec_pro.setLayoutManager(rec_pro_lm);
        rec_gallery.setLayoutManager(rec_gallery_lm);
        rec_bh.setLayoutManager(rec_bh_lm);
        rec_key.setLayoutManager(rec_key_lm);
        rec_training.setLayoutManager(rec_training_lm);
        rec_posts.setLayoutManager(rec_posts_lm);
//        final String com_nm = getIntent().getStringExtra("com_nm");
        String url = "http://rajeshwersoftsolution.com/octopus_biz/public/api/single_user_listing";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.e(TAG,response);
                try {
                    img_url = "http://rajeshwersoftsolution.com/octopus_biz/public/";
                    JSONObject object = new JSONObject(response);
                    JSONObject data = object.getJSONObject("data");
                    String nm = data.getString("name");
                    String description = data.getString("description");
                    String mobile = data.getString("mobile_no");
                    String email = data.getString("email");
                    String logo = data.getString("logo");
                    img_url = img_url+logo;
                    Glide.with(con).load(img_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(img_card_cat);
                    tv_com_nm.setText(nm);
                    tv_com_email.setText(email);
                    tv_com_mobile.setText(mobile);
                    tv_com_add.setText(description);
                    JSONArray cats = data.getJSONArray("category_data");
                    for (int i = 0; i < cats.length(); i++) {
                        JSONObject cat = cats.getJSONObject(i);
                        Integer cat_id = cat.getInt("category_id");
                        String cat_nm = cat.getString("name");
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("cat_id", cat_id);
                        map.put("cat_nm", cat_nm);
                        cat_list.add(map);
                    }
                    categoryDataAdapter = new CategoryDataAdapter(con,cat_list);
                    rec_cat.setAdapter(categoryDataAdapter);
                    JSONArray products = data.getJSONArray("product_listing");
                    for (int a = 0 ; a < products.length() ; a++)
                    {
                        img_url = "http://rajeshwersoftsolution.com/octopus_biz/public/";
                        JSONObject product = products.getJSONObject(a);
                        String pro_nm = product.getString("product_name");
                        Integer pro_price = product.getInt("product_price");
                        String pro_img_url = product.getString("product_description");
                        img_url = img_url+pro_img_url;
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("pro_nm",pro_nm);
                        map.put("pro_price",pro_price);
                        map.put("pro_img_url",img_url);
                        pro_list.add(map);
                    }
                    productsAdapter = new ProductsAdapter(con,pro_list);
                    rec_pro.setAdapter(productsAdapter);
                    JSONArray gallery = data.getJSONArray("gallery_listing");
                    for (int j=0;j<gallery.length();j++)
                    {
                        img_url = "http://rajeshwersoftsolution.com/octopus_biz/public/";
                        JSONObject image = gallery.getJSONObject(j);
                        String img_nm = image.getString("gallery_name");
                        String img_path = image.getString("gallery_image");
                        String img_dt = image.getString("created_dt");
                        img_url = img_url+img_path;
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("img_nm",img_nm);
                        map.put("img_url",img_url);
                        map.put("img_dt",img_dt);
                        gallery_list.add(map);
                    }
                    galleryAdapter = new GalleryAdapter(con,gallery_list);
                    rec_gallery.setAdapter(galleryAdapter);
                    JSONArray bhs = data.getJSONArray("business_hours");
                    for (int b = 0 ; b<bhs.length();b++)
                    {
                        JSONObject bh = bhs.getJSONObject(b);
                        Integer bh_id = bh.getInt("id");
                        String bh_day = bh.getString("day");
                        String bh_st = bh.getString("start_time");
                        String bh_et = bh.getString("end_time");
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("bh_id",bh_id);
                        map.put("bh_day",bh_day);
                        map.put("bh_st",bh_st);
                        map.put("bh_et",bh_et);
                        bh_list.add(map);
                    }
                    businessHoursAdapter = new BusinessHoursAdapter(con,bh_list);
                    rec_bh.setAdapter(businessHoursAdapter);
                    JSONArray keys = data.getJSONArray("keyword_data");
                    for (int c=0 ; c<keys.length();c++)
                    {
                        JSONObject key = keys.getJSONObject(c);
                        Integer key_id = key.getInt("keyword_id");
                        String key_nm = key.getString("keyword_name");
                        HashMap<String,Object> map= new HashMap<>();
                        map.put("key_id",key_id);
                        map.put("key_nm",key_nm);
                        key_list.add(map);
                    }
                    keyWordAdapter = new KeyWordAdapter(con,key_list);
                    rec_key.setAdapter(keyWordAdapter);
                    JSONArray trainings = data.getJSONArray("training_data");
                    for (int d=0;d<trainings.length();d++)
                    {
                        JSONObject training = trainings.getJSONObject(d);
                        Integer training_id = training.getInt("training_id");
                        String training_nm = training.getString("training_name");
                        HashMap<String,Object> map= new HashMap<>();
                        map.put("training_id",training_id);
                        map.put("training_nm",training_nm);
                        training_list.add(map);
                    }
                    trainingAdapter = new TrainingAdapter(con,training_list);
                    rec_training.setAdapter(trainingAdapter);
//                    JSONArray posts = data.getJSONArray("");
//                    for (int e=0;e<posts.length();e++)
//                    {
//                        JSONObject post=posts.getJSONObject(e);
//                        String post_title = post.getString("");
//                        String post_cat = post.getString("");
//                        String post_desc = post.getString("");
//                        String post_url = post.getString("");
//                        HashMap<String,Object> map = new HashMap<>();
//                        map.put("post_title",post_title);
//                        map.put("post_cat",post_cat);
//                        map.put("post_desc",post_desc);
//                        map.put("post_url",post_url);
//                        posts_list.add(map);
//                    }
//                    postsAdapter = new PostsAdapter(con,posts_list);
//                    rec_posts.setAdapter(postsAdapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> params = new HashMap<>();
                params.put("Authorization","MzVmMjJmOTQt");
                return params;
            }



            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String ,String > params = new HashMap<>();
                params.put("user_id","1");
                params.put("company_id","1");
                return params;
            }
        };
        AppController.getmInstance().addToRequestQueue(request);
    }

    private void findviewbyids()
    {
        img_card_cat  = findViewById(R.id.img_com_cat);
        tv_com_nm = findViewById(R.id.tv_com_nm);
        tv_com_email = findViewById(R.id.tv_com_email);
        tv_com_mobile = findViewById(R.id.tv_com_mobile);
        tv_com_add = findViewById(R.id.tv_com_add);
        rec_cat = findViewById(R.id.rec_cat);
        rec_pro = findViewById(R.id.rec_pro);
        rec_gallery = findViewById(R.id.rec_gallery);
        rec_bh = findViewById(R.id.rec_bh);
        rec_posts = findViewById(R.id.rec_posts);
        rec_key = findViewById(R.id.rec_key);
        rec_training = findViewById(R.id.rec_training);
        ll_images = findViewById(R.id.ll_images);
        ll_whole = findViewById(R.id.ll_whole);
    }

    public static void openImages(String s)
    {
        String url = "http://rajeshwersoftsolution.com/octopus_biz/public/api/single_user_listing";
        ll_whole.setVisibility(View.GONE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject object = new JSONObject(response);
                    JSONObject data = object.getJSONObject("data");
                    JSONArray images = data.getJSONArray("gallery_listing");
                    for (int a=0;a<images.length();a++)
                    {
                        JSONObject image = images.getJSONObject(a);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
    }
}
