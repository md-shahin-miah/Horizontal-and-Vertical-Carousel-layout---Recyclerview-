package com.shahin.recyclerviewexample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.recyclerviewexample.adapter.MainAdapter;
import com.shahin.recyclerviewexample.views.RecyclerviewActivity;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainItemClickListener {

    private RecyclerView vertical_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vertical_recyclerview = findViewById(R.id.vertical_recyclerview);


        vertical_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MainAdapter adapter = new MainAdapter(this, Utils.getListOfRecyclerviewType(), this);
        vertical_recyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String typeOfRecyclerview) {

        if (typeOfRecyclerview.equalsIgnoreCase(AppConstants.CAROUSEL_RECYCLERVIEW)) {

            intentForCarousel(AppConstants.CAROUSEL_RECYCLERVIEW);

        } else if (typeOfRecyclerview.equalsIgnoreCase(AppConstants.CUSTOM_ANIMATED_RECYCLERVIEW)) {
            intentForCarousel(AppConstants.CUSTOM_ANIMATED_RECYCLERVIEW);

        } else if (typeOfRecyclerview.equalsIgnoreCase(AppConstants.ANIMATED_ON_ADAPTER_RECYCLERVIEW)) {
            intentForCarousel(AppConstants.ANIMATED_ON_ADAPTER_RECYCLERVIEW);

        }


    }

    private void intentForCarousel(String value) {
        Intent intent = new Intent(this, RecyclerviewActivity.class);
        intent.putExtra(AppConstants.FROM, value);
        startActivity(intent);
    }
}
