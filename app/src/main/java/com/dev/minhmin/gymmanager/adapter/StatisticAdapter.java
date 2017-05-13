package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Practice;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/13/2017.
 */

public class StatisticAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Practice> listPractices = new ArrayList<>();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private String date = "";
    private onCheckedChangeListener mCallback;

    public StatisticAdapter(Activity activity, ArrayList<Practice> listPractices, onCheckedChangeListener callback) {
        this.activity = activity;
        this.listPractices = listPractices;
        this.mCallback = callback;
    }

    public StatisticAdapter(Activity activity, ArrayList<Practice> listPractices, String date, onCheckedChangeListener callback) {
        this.activity = activity;
        this.listPractices = listPractices;
        this.date = date;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return listPractices.size();
    }

    @Override
    public Object getItem(int position) {
        return listPractices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        final Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_statistic, parent, false);
            viewholder = new Viewholder();
            viewholder.tvName = (TextView) view.findViewById(R.id.tv_statistic_name_excer);
            viewholder.tvNumber = (TextView) view.findViewById(R.id.tv_statistic_number_calo);
            viewholder.checkBox = (CheckBox) view.findViewById(R.id.checkbox_statistic);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.tvName.setText(listPractices.get(i).getWorkoutExercise().getName());
        viewholder.tvNumber.setText(listPractices.get(i).getWorkoutExercise().getKalo() + " " + ConstantUtils.unitCalo);
        viewholder.checkBox.setChecked(listPractices.get(i).isChecked());
        viewholder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ref.child("Statistic").child(date).child(listPractices.get(i).getWorkoutExercise().getName()).child("checked");
                if (isChecked) {
                    listPractices.get(i).setChecked(true);
                    ref.setValue(true);
                    mCallback.calculate(i, true);
                } else {
                    ref.setValue(false);
                    listPractices.get(i).setChecked(false);
                    mCallback.calculate(i, false);
                }
            }
        });

        return view;
    }

    public interface onCheckedChangeListener {
        void calculate(int i, boolean value);
    }

    private class Viewholder {
        TextView tvName, tvNumber;
        CheckBox checkBox;
    }
}
