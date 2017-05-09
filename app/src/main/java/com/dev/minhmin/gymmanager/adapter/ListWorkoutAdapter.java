package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Workout;

import java.util.ArrayList;

/**
 * Created by Minh min on 4/19/2017.
 */

public class ListWorkoutAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Workout> listWorkouts = new ArrayList<>();

    public ListWorkoutAdapter(Activity activity, ArrayList<Workout> listWorkouts) {
        this.activity = activity;
        this.listWorkouts = listWorkouts;
    }

    @Override
    public int getCount() {
        return listWorkouts.size();
    }

    @Override
    public Object getItem(int i) {
        return listWorkouts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_list_workout, viewGroup, false);
            viewholder = new Viewholder();
            viewholder.tvTitle = (TextView) view.findViewById(R.id.tv_workout_title);
            viewholder.tvTime = (TextView) view.findViewById(R.id.tv_workout_time);
            viewholder.ivImage = (ImageView) view.findViewById(R.id.iv_workout_image);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.tvTitle.setText(listWorkouts.get(i).getTitle());
        viewholder.tvTime.setText(listWorkouts.get(i).getTime() + " Weeks");
        viewholder.ivImage.setImageResource(listWorkouts.get(i).getThumbUrl());
        return view;
    }

    private class Viewholder {
        TextView tvTitle, tvTime;
        ImageView ivImage;
    }
}