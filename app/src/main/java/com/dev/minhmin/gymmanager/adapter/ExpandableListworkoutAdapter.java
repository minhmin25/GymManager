package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Minh min on 5/12/2017.
 */

public class ExpandableListworkoutAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    //    private ArrayList<WorkoutExercise> listdata = new ArrayList<>();
    private ArrayList<String> listHeader = new ArrayList<>();
    private HashMap<String, ArrayList<WorkoutExercise>> listData = new HashMap<>();

//    public ExpandableListworkoutAdapter(Activity activity, ArrayList<WorkoutExercise> listdata) {
//        this.activity = activity;
//        this.listdata = listdata;
//    }


    public ExpandableListworkoutAdapter(Activity activity, ArrayList<String> listHeader, HashMap<String, ArrayList<WorkoutExercise>> listData) {
        this.activity = activity;
        this.listHeader = listHeader;
        this.listData = listData;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listData.get(listHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listData.get(listHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder holder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_expandable_listview_header, viewGroup, false);
            holder = new GroupHolder();
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_expandable_list_header);
            holder.ivDetail = (ImageView) view.findViewById(R.id.iv_expandable_list_detail);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }
        holder.tvTitle.setText(listHeader.get(i));
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder holder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_expandable_listview_child, viewGroup, false);
            holder = new ChildHolder();
            holder.tvSet = (TextView) view.findViewById(R.id.tv_workout_exercise_set);
            holder.tvQuantity = (TextView) view.findViewById(R.id.tv_expandable_list_quantity);
            holder.tvContent = (TextView) view.findViewById(R.id.tv_expandable_list_content);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        holder.tvSet.setText("Set: " + listData.get(listHeader.get(i)).get(i1).getSet());
        holder.tvQuantity.setText(listData.get(listHeader.get(i)).get(i1).getQuantity() + " " + listData.get(listHeader.get(i)).get(i1).getUnit() + " each set");
        holder.tvContent.setText(listData.get(listHeader.get(i)).get(i1).getContent());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class ChildHolder {
        TextView tvSet, tvQuantity, tvContent;
    }

    private class GroupHolder {
        TextView tvTitle;
        ImageView ivDetail;
    }
}
