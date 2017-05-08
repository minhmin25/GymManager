package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Meal;

/**
 * Created by Administrator on 5/6/2017.
 */

public class MealDetailAdapter extends BaseAdapter {
    private Activity activity;
    private Meal meal;

    public MealDetailAdapter(Activity activity, Meal meal) {
        this.activity = activity;
        this.meal = meal;
    }

    @Override
    public int getCount() {
        return meal.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return meal.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_mead_details, parent, false);
            viewholder = new Viewholder();
            viewholder.tvName = (TextView) view.findViewById(R.id.tv_name_food);
            viewholder.tvNumber = (TextView) view.findViewById(R.id.tv_number_food);
            viewholder.iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
            viewholder.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.tvName.setText(meal.getItems().get(i).getFood().getName());
        String s = meal.getItems().get(i).getFood().getUnit2() + " " + meal.getItems().get(i).getFood().getUnit1() + "(" + meal.getItems().get(i).getTotalCalo() + " Calo";
        viewholder.tvNumber.setText(meal.getItems().get(i).getNumber() + "x" + s);
        viewholder.iv_food.setImageResource(meal.getItems().get(i).getFood().getImageUrl());

        viewholder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        viewholder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    private class Viewholder {
        TextView tvName, tvNumber;
        ImageView iv_edit, iv_delete, iv_food;
    }
}
