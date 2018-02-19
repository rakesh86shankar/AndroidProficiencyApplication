package com.example.demo.demoapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.demo.demoapplication.R;
import com.example.demo.demoapplication.modal.Row;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Row> rowList;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView networkImageView;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.row_title);
            description = view.findViewById(R.id.row_desc);
            networkImageView = view.findViewById(R.id.imageView);
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
        if (!TextUtils.isEmpty(rowObject.getImageHref())) {
            holder.networkImageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(rowObject.getImageHref())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(holder.networkImageView);
        } else {
            holder.networkImageView.setVisibility(View.GONE);
        }
    }


    public int getItemCount() {
        if (rowList != null) {
            return rowList.size();
        }
        return 0;
    }


}
