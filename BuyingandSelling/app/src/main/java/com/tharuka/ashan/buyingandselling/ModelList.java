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
import android.widget.ListView;
import android.widget.TextView;

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
import data.SellDTO;

/**
 * Created by DELL on 24-Feb-18.
 */

public class ModelList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    private List<SellDTO> sellList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modellist);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String val = bundle.getSerializable("main").toString();

            new Call().execute(val);
        }

        listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);


    }


    private void fillData(String responce) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<SellDTO>>() {
        }.getType();
        sellList = gson.fromJson(responce, listType);
        listView.setAdapter(new MyAdaptor());

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SellDTO sell = sellList.get(position);
        Intent intent = new Intent(ModelList.this, Buy.class);
        Bundle bundle = new Bundle();

        bundle.putString("sellId", "" + sell.getSellId());
        bundle.putString("modelName",sell.getModelName());
        bundle.putString("price",Double.toString(sell.getPrice()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class MyAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return sellList.size();
        }

        @Override
        public Object getItem(int i) {
            return sellList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(ModelList.this).inflate(R.layout.modellistview, null);

            SellDTO sell = sellList.get(position);

            TextView itemName = view.findViewById(R.id.modelnameeee);

            itemName.setText(sell.getModelName());


            return view;
        }

    }

    class Call extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress +":8080/BuyingSellingSystem/getModelsByBrand");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("brand", param[0]));



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

