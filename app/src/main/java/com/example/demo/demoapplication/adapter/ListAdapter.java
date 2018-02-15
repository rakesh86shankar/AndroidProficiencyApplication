package com.example.demo.demoapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo.demoapplication.R;
import com.example.demo.demoapplication.modal.Row;

import java.util.List;

/**
 * Created by RA283478 on 2/15/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Row> rowList;
    Context context;


    public ListAdapter(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        ImageView networkImageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_title);
            description = (TextView) view.findViewById(R.id.row_desc);
            networkImageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public void setRows(List<Row> rowListData) {
        rowList = rowListData;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Row rowObject = rowList.get(position);
        holder.title.setText(rowObject.getTitle());
        holder.description.setText(rowObject.getDescription());
        if(!TextUtils.isEmpty(rowObject.getImageHref())){
            holder.networkImageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(rowObject.getImageHref())
                    .thumbnail(0.5f)
                    .crossFade()
                    .into(holder.networkImageView);
        }else {
            holder.networkImageView.setVisibility(View.GONE);
        }

       /* Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        picasso.setDebugging(true);
        picasso.load(rowObject.getImageHref())
                .resize(50,50).into(holder.networkImageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("onSuccess",rowObject.getImageHref());
            }

            @Override
            public void onError() {
                Log.e("Error",rowObject.getImageHref());
            }
        });*/

    }


    public int getItemCount() {
        if (rowList != null) {
            return rowList.size();
        }
        return 0;
    }


}
