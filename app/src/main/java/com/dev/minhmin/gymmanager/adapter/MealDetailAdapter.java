package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.DataCenter;

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
        viewholder.iv_food.setImageResource(meal.getItems().get(i).getFood().getImage());
        final String idFood = meal.getItems().get(i).getFood().getId();
        final String date = meal.getDate();
        final String type = meal.getType();

        viewholder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout = (RelativeLayout) activity.findViewById(R.id.relayout_add_food);


            }
        });

        viewholder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Do you really want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCenter dataCenter = new DataCenter();
                        dataCenter.deleteItem(idFood, date, type);
                        notifyDataSetChanged();
                        Toast.makeText(activity, "Deleted successfully", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
        return view;
    }


    private class Viewholder {
        TextView tvName, tvNumber;
        ImageView iv_edit, iv_delete, iv_food;
    }
}
