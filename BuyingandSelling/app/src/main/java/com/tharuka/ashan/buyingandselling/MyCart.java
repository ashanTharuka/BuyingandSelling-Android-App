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

public class MyCart extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);
        Button view1 =  findViewById(R.id.bbbybtn);
        view1.setOnClickListener(this);
        Button view2 =  findViewById(R.id.sellllbtn);
        view2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bbbybtn) {
            Intent intent = new Intent(this,BuyingItemsList.class);
            startActivity(intent);
        }
        finish();
        if (v.getId() == R.id.sellllbtn) {
            Intent intent = new Intent(this,SellingItemsList.class);
            startActivity(intent);
        }
        finish();
    }
}