package com.shahin.recyclerviewexample.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.recyclerviewexample.R;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {
    private Context context;

    List<String> listOfItem;

    public CarouselAdapter(Context context,List<String> list) {
        this.context = context;
        this.listOfItem=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        int resourceId = R.layout.single_row_main;
        final LayoutInflater mInflater;
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(resourceId, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Log.d(TAG, "onBindViewHolder: position "+listOfItem.get(position));

        holder.tvHeader.setText(listOfItem.get(position));

    }

    @Override
    public int getItemCount() {
        return listOfItem.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvHeader;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvHeader = itemView.findViewById(R.id.header);
        }
    }
}
