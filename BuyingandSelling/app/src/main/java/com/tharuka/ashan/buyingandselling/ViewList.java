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
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;
import data.LocationDTO;

/**
 * Created by DELL on 21-Feb-18.
 */

public class ViewList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private String val;
    private List<LocationDTO> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            val = bundle.getSerializable("val").toString();
        }

        startService();
        listview = findViewById(R.id.list);
        listview.setOnItemClickListener(this);

    }

    private void fillData(String responce) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<LocationDTO>>() {
        }.getType();
        locationList = gson.fromJson(responce, listType);
        listview.setAdapter(new MyAdapter());

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LocationDTO loc = locationList.get(position);
      if (val.equalsIgnoreCase("sign")){
          Intent intent = new Intent(ViewList.this, SignUp.class);
          Bundle bundle = new Bundle();
          bundle.putSerializable("lname", loc.getLocationName());
          bundle.putSerializable("lid", loc.getLocationId());
          intent.putExtras(bundle);
          startActivity(intent);
      }else if(val.equalsIgnoreCase("loc")){
          Intent intent = new Intent(ViewList.this, Location.class);
          Bundle bundle = new Bundle();
          bundle.putSerializable("lname", loc.getLocationName());
          bundle.putSerializable("lid", loc.getLocationId());
          intent.putExtras(bundle);
          startActivity(intent);
      }

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return locationList.size();
        }

        @Override
        public Object getItem(int i) {
            return locationList.get(i);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView contactNametextView;
            LocationDTO loc = locationList.get(position);

            view = LayoutInflater.from(ViewList.this).inflate(R.layout.locationlistview, null);
            contactNametextView = view.findViewById(R.id.locationname);
            contactNametextView.setText(loc.getLocationName());

            return view;
        }
    }

    private void startService() {

        class MyAsync extends AsyncTask<String, String, String> {
            ProgressDialog progress = new ProgressDialog(ViewList.this);

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
                    HttpGet httpget = new HttpGet("http://"+ IpAddress.ipaddress+":8080/BuyingSellingSystem/getAllLocation");

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
