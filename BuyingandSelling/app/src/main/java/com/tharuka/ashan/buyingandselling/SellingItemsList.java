package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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

/**
 * Created by DELL on 23-Feb-18.
 */

public class SellingItemsList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellingitemlist);


            new Call().execute(CustomerView.id);


        listview = findViewById(R.id.list);
        listview.setOnItemClickListener(this);

    }

    private void fillData(String responce) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        itemList = gson.fromJson(responce, listType);
        listview.setAdapter(new MyAdapter());

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


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
            String name = itemList.get(position);

            view = LayoutInflater.from(SellingItemsList.this).inflate(R.layout.sellingitemlistview, null);
            contactNametextView = view.findViewById(R.id.sellingitemname);
            contactNametextView.setText(name);

            return view;
        }
    }

    class Call extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/findMySells");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));


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
