package com.dev.minhmin.gymmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/9/2017.
 */

public class ListExerciseWorkoutAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<WorkoutExercise> listExercise;


    public ListExerciseWorkoutAdapter(Context context, ArrayList<WorkoutExercise> listExercise) {
        this.context = context;
        this.listExercise = listExercise;
    }

    @Override
    public int getGroupCount() {
        return listExercise.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listExercise.size();
    }

    @Override
    public Object getGroup(int i) {
        return listExercise.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listExercise.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        HeaderViewholder viewholder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_workout_exercise_header, viewGroup, false);
            viewholder = new HeaderViewholder();
            viewholder.tvHeader = (TextView) view.findViewById(R.id.tv_workout_exercise_header);
            viewholder.ivDetail = (ImageView) view.findViewById(R.id.iv_workout_exercise_detail);
            view.setTag(viewholder);
//            view = inflater.inflate(R.layout.item_list_workout_exercise_header, null);
        } else {
            viewholder = (HeaderViewholder) view.getTag();
        }
        viewholder.tvHeader.setText(listExercise.get(i).getName());
        viewholder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT);
            }
        });
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewholder viewholder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_workout_exercise_child, viewGroup, false);
//            view = inflater.inflate(R.layout.item_list_workout_exercise_child, null);
            viewholder = new ChildViewholder();
            viewholder.tvSet = (TextView) view.findViewById(R.id.tv_workout_exercise_set);
            viewholder.tvQuantity = (TextView) view.findViewById(R.id.tv_workout_exercise_quantity);
            viewholder.tvContent = (TextView) view.findViewById(R.id.tv_workout_exercise_content);
            view.setTag(viewholder);
        } else {
            viewholder = (ChildViewholder) view.getTag();
        }
        viewholder.tvSet.setText(listExercise.get(i).getSet() + " Set");
        viewholder.tvQuantity.setText(listExercise.get(i).getQuantity() + " " + listExercise.get(i).getQuantity() + " each set");
        viewholder.tvContent.setText(listExercise.get(i).getContent());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class HeaderViewholder {
        TextView tvHeader;
        ImageView ivDetail;
    }

    private class ChildViewholder {
        TextView tvSet, tvQuantity, tvContent;
    }
}
