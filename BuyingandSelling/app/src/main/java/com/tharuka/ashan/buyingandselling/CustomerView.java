package com.tharuka.ashan.buyingandselling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by DELL on 20-Feb-18.
 */

public class CustomerView extends AppCompatActivity implements View.OnClickListener {
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_view);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            id = bundle.getSerializable("id").toString();

        }
        Button view1 = findViewById(R.id.cclogoutbtn);
        View view2 = findViewById(R.id.ccbuy);
        View view3 = findViewById(R.id.ccsell);
        View view4 = findViewById(R.id.ccprofile);
        View view5 = findViewById(R.id.ccmycart);
        View view6= findViewById(R.id.cclogoutbtn);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
        view5.setOnClickListener(this);
        view6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cclogoutbtn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ccsell) {
            Intent intent = new Intent(this, Sell.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ccbuy) {
            Intent intent = new Intent(this, Buy.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ccprofile) {
            Intent intent = new Intent(this, CustomerProfile.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("id",id );
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v.getId() == R.id.ccmycart) {
            Intent intent = new Intent(this, MyCart.class);
            startActivity(intent);
        }

    }
}