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

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.CustomerDTO;
import data.IpAddress;
import data.ItemDTO;

/**
 * Created by DELL on 22-Feb-18.
 */

public class ItemList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    private List<ItemDTO> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist);

        listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        startService();

    }


    private void fillData(String responce) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ItemDTO>>() {
        }.getType();
        itemList = gson.fromJson(responce, listType);
        listView.setAdapter(new MyAdaptor());

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ItemDTO cus = itemList.get(position);
        Intent intent = new Intent(ItemList.this, Item.class);
        Bundle bundle = new Bundle();

        bundle.putString("i", "" + cus.getItemId());
        bundle.putString("in", cus.getItemName());
        bundle.putString("bn", "" + cus.getBrandName());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class MyAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return itemList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(ItemList.this).inflate(R.layout.itemlistview, null);

            ItemDTO cus = itemList.get(position);

            TextView itemName = view.findViewById(R.id.listitemname);

            itemName.setText(cus.getBrandName());


            return view;
        }
    }

    private void startService() {

        class MyAsync extends AsyncTask<String, String, String> {
            ProgressDialog progress = new ProgressDialog(ItemList.this);

            public MyAsync() {
                progress.setTitle("Server Call");
                progress.setMessage("Connecting.. Please Wait! ");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setIndeterminate(true);
                progress.show();
            }

            @Override
            protected String doInBackground(String... voids) {
                try {

                    HttpClient client = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/getAllItems");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();

                    String responce = client.execute(httpget, responseHandler);
                    return responce;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(String responce) {
                super.onPostExecute(responce);
                progress.dismiss();
                if (responce != null) {

                    fillData(responce);
                }
            }
        }
        new MyAsync().execute();
    }
}