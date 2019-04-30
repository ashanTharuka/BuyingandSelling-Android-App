package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.IpAddress;

/**
 * Created by DELL on 20-Feb-18.
 */

public class Sell extends AppCompatActivity implements View.OnClickListener {

    private static String main ;
    String id;
    String itemid = "";
    String brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell);
        id = CustomerView.id;

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            try {
                if (bundle.getSerializable("main").toString() != null) {
                    main = bundle.getSerializable("main").toString();
                    TextView t = findViewById(R.id.itypetext);
                    t.setText(main);
                }

            } catch (Exception ex) {
            }
            try {

                if (bundle.getSerializable("brand").toString() != null) {

                    itemid = bundle.getSerializable("iid").toString();
                    brand = bundle.getSerializable("brand").toString();
                    TextView t = findViewById(R.id.branasddtext);
                    t.setText(brand);
                    TextView tt = findViewById(R.id.itypetext);
                    tt.setText(main);
                }

            } catch (Exception ex) {
            }


        }
        Button view1 = findViewById(R.id.itypebtn);
        view1.setOnClickListener(this);
        Button view2 = findViewById(R.id.ibrandbtn);
        view2.setOnClickListener(this);
        Button view3 = findViewById(R.id.buybtnxx);
        view3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itypebtn) {
            Intent intent = new Intent(this, MainCategoryList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val", "sell");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.ibrandbtn) {
            Intent intent = new Intent(this, BrandList.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("val", "sell");
            bundle.putSerializable("main", main);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.buybtnxx) {

            EditText modelname = findViewById(R.id.modellnametext);
            EditText price = findViewById(R.id.priicetext);
            new Add().execute(price.getText().toString(), modelname.getText().toString(), id, itemid);
        }

    }

    class Add extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... param) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://" + IpAddress.ipaddress + ":8080/BuyingSellingSystem/addSell");
                List<NameValuePair> list = new ArrayList<>();

                list.add(new BasicNameValuePair("price", param[0]));
                list.add(new BasicNameValuePair("modelName", param[1]));
                list.add(new BasicNameValuePair("cusId", param[2]));
                list.add(new BasicNameValuePair("itemid", param[3]));


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
            EditText modelname = findViewById(R.id.modellnametext);
            EditText price = findViewById(R.id.priicetext);
            price.setText("");
            modelname.setText("");
            Toast.makeText(getApplicationContext(), "Item Added" + response, Toast.LENGTH_SHORT).show();


        }
    }
}