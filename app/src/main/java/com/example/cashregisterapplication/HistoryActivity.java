package com.example.cashregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView listView;
    private static
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listview);

        adapter = new CustomAdapter(MainActivity.purchase, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerAdapter adapter = new RecyclerAdapter(this, MainActivity.purchase);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(adapter);
    }
}