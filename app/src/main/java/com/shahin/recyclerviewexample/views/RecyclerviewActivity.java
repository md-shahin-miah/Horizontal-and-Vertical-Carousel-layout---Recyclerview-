package com.shahin.recyclerviewexample.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahin.recyclerviewexample.AppConstants;
import com.shahin.recyclerviewexample.R;
import com.shahin.recyclerviewexample.Utils;
import com.shahin.recyclerviewexample.adapter.AnimatedAdapter;
import com.shahin.recyclerviewexample.adapter.CarouselAdapter;
import com.shahin.recyclerviewexample.adapter.CustomAnimatedAdapter;
import com.shahin.recyclerviewexample.carousel.CarouselLayoutManagerCustom;
import com.shahin.recyclerviewexample.carousel.CarouselZoomPostLayoutListenerCustom;
import com.shahin.recyclerviewexample.carousel.CenterScrollListenerCustom;
import com.shahin.recyclerviewexample.carousel.DefaultChildSelectionListenerCustom;

import java.util.Locale;

public class RecyclerviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "RecyclerviewActivity";

    RecyclerView recyclerViewHorizontal, recyclerViewVertical;

    TextView textViewHorizontal;

    Spinner spinner;

    RelativeLayout relativeLayoutVertical,relativeLayoutHorizontal;
    Intent intent;
    String from;

    String[] animationType = {"layout_animation_fall_down", "layout_animation_from_bottom", "layout_animation_from_left", "layout_animation_from_right"};
//    "item_animation_fall_down", "item_animation_from_bottom", "item_animation_from_left", "item_animation_from_right"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerViewHorizontal = findViewById(R.id.recyclerview_horizontal);
        recyclerViewVertical = findViewById(R.id.recyclerview_vertical);
        textViewHorizontal = findViewById(R.id.horizontal_text);
        relativeLayoutVertical = findViewById(R.id.vertical_RelativeLayout);
        relativeLayoutHorizontal = findViewById(R.id.horizontal_RelativeLayout);
        spinner = findViewById(R.id.spinner);





        try {
            intent = getIntent();

            from = intent.getStringExtra(AppConstants.FROM);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getStringExtra_EX", e + "");
        }


        Log.d(TAG, "onCreate: from  " + from);

        if (from.equalsIgnoreCase(AppConstants.CAROUSEL_RECYCLERVIEW)) {
            carouselVertical();
            carouselHorizontal();
        } else if (from.equalsIgnoreCase(AppConstants.ANIMATED_ON_ADAPTER_RECYCLERVIEW)) {

            animatedOnAdapterSetup();

        } else if (from.equalsIgnoreCase(AppConstants.CUSTOM_ANIMATED_RECYCLERVIEW)) {
            relativeLayoutHorizontal.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);
            spinner.setOnItemSelectedListener(this);

            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, animationType);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(aa);



        }


    }

    private void customAnimatedRecyclerview(String type) {




        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CustomAnimatedAdapter animatedAdapter = new CustomAnimatedAdapter(this, true, Utils.getList());
        recyclerViewVertical.setAdapter(animatedAdapter);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, getResources().getIdentifier(type, "anim", getPackageName()));
        recyclerViewVertical.setLayoutAnimation(animation);
        animatedAdapter.notifyDataSetChanged();
        recyclerViewVertical.scheduleLayoutAnimation();


    }

    private void animatedOnAdapterSetup() {
//        -------------------------VERTICAL-------------------------------------
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AnimatedAdapter animatedAdapterVertical = new AnimatedAdapter(this, false, Utils.getList());
        recyclerViewVertical.setAdapter(animatedAdapterVertical);

//        -------------------------HORIZONTAL-------------------------------------

        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AnimatedAdapter animatedAdapterHorizontal = new AnimatedAdapter(this, true, Utils.getList());
        recyclerViewHorizontal.setAdapter(animatedAdapterHorizontal);


    }

    private void carouselVertical() {
        recyclerViewVertical.setVisibility(View.VISIBLE);
        final CarouselLayoutManagerCustom layoutManager = new CarouselLayoutManagerCustom(CarouselLayoutManagerCustom.VERTICAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListenerCustom());
        layoutManager.setMaxVisibleItems(6);
        Log.d(TAG, "onCreate: getList " + Utils.getList());
        CarouselAdapter carouselAdapter = new CarouselAdapter(this, Utils.getList());
        recyclerViewVertical.setLayoutManager(layoutManager);
        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setAdapter(carouselAdapter);
        recyclerViewVertical.addOnScrollListener(new CenterScrollListenerCustom());


        DefaultChildSelectionListenerCustom.initCenterItemListener((recyclerView, carouselLayoutManager, v) -> {
            final int position = recyclerView.getChildLayoutPosition(v);
            final String msg = String.format(Locale.US, "Item %1$d was clicked", position);

            Log.d(TAG, "rootMenuRecyclerSetUpLeft: position " + position);
        }, recyclerViewVertical, layoutManager);


        layoutManager.addOnItemSelectionListener(adapterPosition -> {
            Log.d(TAG, "addOnItemSelectionListener: adapter pos " + adapterPosition + " visibleMainMenu " + adapterPosition);
            if (CarouselLayoutManagerCustom.INVALID_POSITION != adapterPosition) {

                Log.d(TAG, "onCenterItemChanged: adapterPosition " + adapterPosition);

            }
        });

    }

    private void carouselHorizontal() {
        recyclerViewHorizontal.setVisibility(View.VISIBLE);

        final CarouselLayoutManagerCustom layoutManager = new CarouselLayoutManagerCustom(CarouselLayoutManagerCustom.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListenerCustom());
        layoutManager.setMaxVisibleItems(6);
        Log.d(TAG, "onCreate: getList " + Utils.getList());
        CarouselAdapter carouselAdapter = new CarouselAdapter(this, Utils.getList());
        recyclerViewHorizontal.setLayoutManager(layoutManager);
        recyclerViewHorizontal.setHasFixedSize(true);
        recyclerViewHorizontal.setAdapter(carouselAdapter);
        recyclerViewHorizontal.addOnScrollListener(new CenterScrollListenerCustom());


        DefaultChildSelectionListenerCustom.initCenterItemListener((recyclerView, carouselLayoutManager, v) -> {
            final int position = recyclerView.getChildLayoutPosition(v);
            final String msg = String.format(Locale.US, "Item %1$d was clicked", position);

            Log.d(TAG, "rootMenuRecyclerSetUpLeft: position " + position);
        }, recyclerViewHorizontal, layoutManager);


        layoutManager.addOnItemSelectionListener(adapterPosition -> {
            Log.d(TAG, "addOnItemSelectionListener: adapter pos " + adapterPosition + " visibleMainMenu " + adapterPosition);
            if (CarouselLayoutManagerCustom.INVALID_POSITION != adapterPosition) {

                Log.d(TAG, "onCenterItemChanged: adapterPosition " + adapterPosition);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        Log.d(TAG, "onItemSelected: " + animationType[i]);
        customAnimatedRecyclerview(animationType[i]);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}