package com.example.cashregisterapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView product, total, quantity;
    Button buy, manager;
    NumberPicker numberPicker;
    ListView listView;

    static ArrayList<productModel> dataModels;
    static ArrayList<productModel> purchase = new ArrayList<>();;
    int qq;
    double pp;
    String name;
    boolean pr = false;
    int ids;

    static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product = findViewById(R.id.product);
        total = findViewById(R.id.total);
        quantity = findViewById(R.id.quantity);
        buy = findViewById(R.id.buy);
        numberPicker = findViewById(R.id.numberPicker);
        listView = findViewById(R.id.listview);
        manager = findViewById(R.id.manager);

        dataModels = new ArrayList<>();

        dataModels.add(new productModel("Pante", 10, 20.44));
        dataModels.add(new productModel("Shoes", 100, 10.44));
        dataModels.add(new productModel("Hats", 30, 5.9));

        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                productModel dataModel = dataModels.get(position);
                numberPicker.setValue(1);
                quantity.setText(String.valueOf(1));
                total.setText(String.valueOf(pp * 1));
                ids = position;
                pr = true;
                name = dataModel.name;
                product.setText(name);
                quantity.setText(String.valueOf(1));
                qq = dataModel.quantity;
                total.setText(String.valueOf(dataModel.price));
                pp = dataModel.price;
            }
        });
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setWrapSelectorWheel(true);


        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (!pr) {
                    Toast.makeText(MainActivity.this, "Select A Product First..", Toast.LENGTH_SHORT).show();
                    numberPicker.setValue(1);
                    quantity.setText(String.valueOf(1));
                    total.setText(String.valueOf(pp * 1));
                } else {
                    if (qq < newVal) {
                        Toast.makeText(MainActivity.this, "Not Enough Quantity In Stock..", Toast.LENGTH_SHORT).show();
                    }else{
                        quantity.setText(String.valueOf(newVal));
                        total.setText(String.valueOf(pp * newVal));
                    }
                }
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pr) {
                    int qt = Integer.parseInt(quantity.getText().toString());
                    if (name == null) {
                        Toast.makeText(MainActivity.this, "All Fields Are Required..", Toast.LENGTH_SHORT).show();
                    } else if (dataModels.get(ids).quantity < qt) {
                        Toast.makeText(MainActivity.this, "Not Enough Quantity In Stock..", Toast.LENGTH_SHORT).show();
                    } else if (dataModels.get(ids).quantity <= 0) {
                        Toast.makeText(MainActivity.this, "Not Enough Quantity In Stock..", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        final View customLayout = getLayoutInflater().inflate(R.layout.dialog, null);
                        TextView t1 = customLayout.findViewById(R.id.t1);
                        TextView t2 = customLayout.findViewById(R.id.t2);
                        t1.setText("Thank You For Your Purchase.");
                        t2.setText("Your Purchase is "+ qt + " " + dataModels.get(ids).name+ " for " +  total.getText().toString());

                        builder.setView(customLayout);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dataModels.set(ids, new productModel(dataModels.get(ids).name, dataModels.get(ids).quantity - qt, dataModels.get(ids).price));
                        purchase.add( new productModel(dataModels.get(ids).name, qt, Double.parseDouble(total.getText().toString()), new Date()));
                        adapter.notifyDataSetChanged();
                        total.setText("Total");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Select A Product First..", Toast.LENGTH_SHORT).show();

                }
            }
        });
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ManagerActivity.class));
            }
        });
    }
}