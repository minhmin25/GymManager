package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/9/2017.
 */

public class ListExerciseWorkoutAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<WorkoutExercise> listData = new ArrayList<>();

    public ListExerciseWorkoutAdapter(Activity activity, ArrayList<WorkoutExercise> listData) {
        this.activity = activity;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_list_workout_exercise, viewGroup, false);
            viewholder = new Viewholder();
            viewholder.tvName = (TextView) view.findViewById(R.id.tv_workout_exercise_name);
            viewholder.tvSet = (TextView) view.findViewById(R.id.tv_workout_exercise_set);
            viewholder.tvQuantity = (TextView) view.findViewById(R.id.tv_workout_exercise_quantity);
            viewholder.tvContent = (TextView) view.findViewById(R.id.tv_workout_exercise_content);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.tvName.setText(listData.get(i).getName());
        viewholder.tvSet.setText("Set: " + listData.get(i).getSet() + "");
        viewholder.tvQuantity.setText(listData.get(i).getQuantity() + " " + listData.get(i).getUnit() + " each set");
        viewholder.tvContent.setText(listData.get(i).getContent());
        return view;
    }

    private class Viewholder {
        private TextView tvName, tvSet, tvQuantity, tvContent;
    }

}
