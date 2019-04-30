package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

import data.CustomerDTO;
import data.IpAddress;
import data.ItemDTO;

/**
 * Created by DELL on 20-Feb-18.
 */

public class Item extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String idd = bundle.getString("i");
            String itemName = bundle.getString("in");
            String brandName = bundle.getString("bn");


            EditText id = findViewById(R.id.xxitemid);
            EditText itemn = findViewById(R.id.xxiitemna);
            EditText brandn = findViewById(R.id.xxiibrandname);
            id.setText(idd);
            itemn.setText(itemName);
            brandn.setText(brandName);
        }
        Button view1 = findViewById(R.id.xxiaddbtn);
        Button view2 = findViewById(R.id.xxideletebtn);
        Button view3 = findViewById(R.id.xxiupdatebtn);
        Button view4 = findViewById(R.id.xxiviewallbtn);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.xxiaddbtn) {


            EditText itemn = findViewById(R.id.xxiitemna);
            EditText brandn = findViewById(R.id.xxiibrandname);

            String itemName = itemn.getText().toString();
            String brandName = brandn.getText().toString();
            new Add().execute(itemName,brandName);
        }
        if (v.getId() == R.id.xxideletebtn) {
            EditText idd = findViewById(R.id.xxitemid);
            String id = idd.getText().toString();
            new Delete().execute(id);
        }
        if (v.getId() == R.id.xxiupdatebtn) {
            EditText idd = findViewById(R.id.xxitemid);
            EditText itemn = findViewById(R.id.xxiitemna);
            EditText brandn = findViewById(R.id.xxiibrandname);

            String id = idd.getText().toString();
            String itemName = itemn.getText().toString();
            String brandName = brandn.getText().toString();
            new Update().execute(id, itemName, brandName);
        }
        if (v.getId() == R.id.xxiviewallbtn) {
            Intent intent = new Intent(this, ItemList.class);
            startActivity(intent);
        }
    }

    class Update extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/updateItem");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("id", param[0]));
                list.add(new BasicNameValuePair("itemName", param[1]));
                list.add(new BasicNameValuePair("brandName", param[2]));


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
            Gson gson = new Gson();
            Type listType = new TypeToken<ItemDTO>() {
            }.getType();
            ItemDTO item = gson.fromJson(response, listType);


            EditText id = findViewById(R.id.xxitemid);
            EditText itemn = findViewById(R.id.xxiitemna);
            EditText brandn = findViewById(R.id.xxiibrandname);
            id.setText("" + item.getItemId());
            itemn.setText(item.getItemName());
            brandn.setText(item.getBrandName());
            Toast.makeText(getApplicationContext(), "Item Updated ", Toast.LENGTH_SHORT).show();


        }
    }

    class Add extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/addItem");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("itemName", param[0]));
                list.add(new BasicNameValuePair("brandName", param[1]));


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

            Toast.makeText(getApplicationContext(), "Item " + response, Toast.LENGTH_SHORT).show();


        }
    }

    class Delete extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://"+ IpAddress.ipaddress +":8080/BuyingSellingSystem/deleteItem");
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
            EditText id = findViewById(R.id.xxitemid);
            EditText itemn = findViewById(R.id.xxiitemna);
            EditText brandn = findViewById(R.id.xxiibrandname);
            id.setText("");
            itemn.setText("");
            brandn.setText("");
            Toast.makeText(getApplicationContext(), "Item Delete "+response, Toast.LENGTH_SHORT).show();


        }
    }

}