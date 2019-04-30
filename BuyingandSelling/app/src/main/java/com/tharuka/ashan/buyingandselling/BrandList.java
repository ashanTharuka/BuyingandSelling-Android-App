package com.tharuka.ashan.buyingandselling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;
import data.ItemDTO;
import data.LocationDTO;

/**
 * Created by DELL on 23-Feb-18.
 */

public class BrandList  extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private String val;
    private List<ItemDTO> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            val = bundle.getSerializable("val").toString();
            String main = bundle.getSerializable("main").toString();
            new Call().execute(main);
        }


        listview = findViewById(R.id.list);
        listview.setOnItemClickListener(this);

    }

    private void fillData(String responce) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ItemDTO>>() {
        }.getType();
        itemList = gson.fromJson(responce, listType);
        listview.setAdapter(new MyAdapter());

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ItemDTO loc = itemList.get(position);
        if (val.equalsIgnoreCase("sell")){
            Intent intent = new Intent(BrandList.this, Sell.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("iid", ""+loc.getItemId());
            bundle.putSerializable("brand", loc.getBrandName());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(val.equalsIgnoreCase("buy")){
            Intent intent = new Intent(BrandList.this, Buy.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("iid", ""+loc.getItemId());
            bundle.putSerializable("brand", loc.getBrandName());
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return itemList.get(i);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView contactNametextView;
            ItemDTO loc = itemList.get(position);

            view = LayoutInflater.from(BrandList.this).inflate(R.layout.brandlistview, null);
            contactNametextView = view.findViewById(R.id.brandccc);
            contactNametextView.setText(loc.getBrandName());

            return view;
        }
    }

    class Call extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress +":8080/BuyingSellingSystem/getBrandByMainCategory");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("mainName", param[0]));



                httpPost.setEntity(new UrlEncodedFormEntity(list));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String response = httpClient.execute(httpPost, responseHandler);

                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {

            super.onPostExecute(response);
            if (response != null) {
                fillData(response);
            }


        }
    }


}
