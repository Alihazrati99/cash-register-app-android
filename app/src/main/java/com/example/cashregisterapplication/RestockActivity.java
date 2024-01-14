package com.example.cashregisterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class RestockActivity extends AppCompatActivity {

    CustomAdapter adapter;
    EditText et;
    Button ok, cancel;
    ListView listView;
    boolean selected = false;
    productModel productModel ;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        et = findViewById(R.id.et);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);

        listView = findViewById(R.id.listview);

        adapter = new CustomAdapter(MainActivity.dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                productModel = MainActivity.dataModels.get(position);
                ids = position;
                selected =true;

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String ii = et.getText().toString();
                if(!selected){
                    Toast.makeText(RestockActivity.this, "All Fields Are Required..", Toast.LENGTH_SHORT).show();

                }else if(ii.length()<=0){

                    Toast.makeText(RestockActivity.this, "All Fields Are Required..", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dataModels.set(ids, new productModel(productModel.name, productModel.quantity + Integer.parseInt(ii), productModel.price));
                    adapter.notifyDataSetChanged();
                    MainActivity.adapter.notifyDataSetChanged();
                }
            }
        });
    }
}