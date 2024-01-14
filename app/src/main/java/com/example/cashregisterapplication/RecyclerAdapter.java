package com.example.cashregisterapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter
        extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<productModel> mData;
    private LayoutInflater mInflater;

    RecyclerAdapter(Context context,ArrayList<productModel>  data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        productModel dataModel = MainActivity.purchase.get(position);

        holder.txtName.setText( dataModel.name);
        holder.txtPrice.setText(String.valueOf(dataModel.price));
        holder.txtQuantity.setText(String.valueOf(dataModel.quantity));
        holder.info.setOnClickListener(view -> {
            Intent i = new Intent(mInflater.getContext(), DetailsActivity.class);
            i.putExtra("name", dataModel.name);
            i.putExtra("price", String.valueOf(dataModel.price));
            i.putExtra("quantity", String.valueOf(dataModel.quantity));
            i.putExtra("date", String.valueOf(dataModel.pur));
            mInflater.getContext().startActivity(i);
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPrice;
        TextView txtQuantity;
        RelativeLayout info;
        ViewHolder(View convertView) {
            super(convertView);
            this.txtName = (TextView) convertView.findViewById(R.id.name);
            this.txtPrice = (TextView) convertView.findViewById(R.id.price);
            this.txtQuantity = (TextView) convertView.findViewById(R.id.quantity);
            this.info = convertView.findViewById(R.id.info);
        }

    }

}