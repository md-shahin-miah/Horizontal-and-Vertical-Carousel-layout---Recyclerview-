package com.shahin.recyclerviewexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.recyclerviewexample.R;

import java.util.List;

public class AnimatedAdapter extends RecyclerView.Adapter<AnimatedAdapter.ViewHolder> {
    private Context context;
    private Boolean aBoolean;
    private List<String> listOfItem;

    public AnimatedAdapter(Context context, Boolean aBoolean,List<String> list) {
        this.context = context;
        this.aBoolean = aBoolean;
        this.listOfItem = list;
    }
    private void setAnimation(View viewToAnimate) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.1f, 1.0f
                , Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f
        );
        anim.setDuration(1000);
        viewToAnimate.startAnimation(anim);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (aBoolean == true) {
            View view = LayoutInflater.from(context).inflate(R.layout.single_row_main, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.single_row_main_vertical, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setAnimation(holder.itemView);

        holder.headerText.setText(listOfItem.get(position));


    }

    @Override
    public int getItemCount() {
        return listOfItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView headerText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headerText=itemView.findViewById(R.id.header);

        }
    }
}
