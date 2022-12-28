package com.shahin.recyclerviewexample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.recyclerviewexample.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private Boolean aBoolean;
    List<String> listOfItem;
    MainItemClickListener mainItemClickListener;

    public MainAdapter(Context context, List<String> list,MainItemClickListener mainItemClickListener) {
        this.context = context;
        this.listOfItem = list;
        this.mainItemClickListener=mainItemClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.type_main_vertical, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.typeOfRecyclerview.setText(listOfItem.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mainItemClickListener!=null){
                    mainItemClickListener.onItemClick(listOfItem.get(position));
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listOfItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeOfRecyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeOfRecyclerview = itemView.findViewById(R.id.tv_typeOfRv);
        }
    }




    public  interface MainItemClickListener{
        void onItemClick(String s);

    }
}
