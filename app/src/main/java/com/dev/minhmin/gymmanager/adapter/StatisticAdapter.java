package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.model.Practice;
import com.dev.minhmin.gymmanager.model.Statistic;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/13/2017.
 */

public class StatisticAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Practice> listitem;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private String date = "";

    public StatisticAdapter(Activity activity, ArrayList<Practice> listitem) {
        this.activity = activity;
        this.listitem = listitem;
    }

    public StatisticAdapter(Activity activity, ArrayList<Practice> listitem, String date) {
        this.activity = activity;
        this.listitem = listitem;
        this.date = date;
    }

    @Override
    public int getCount() {
        return listitem.size();
    }

    @Override
    public Object getItem(int position) {
        return listitem.get(position);
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
        viewholder.tvName.setText(listitem.get(i).getWorkoutExercise().getName());
        viewholder.tvNumber.setText(listitem.get(i).getWorkoutExercise().getKalo() + " " + ConstantUtils.unitCalo);
        viewholder.checkBox.setChecked(listitem.get(i).isChecked());
        viewholder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ///datacenterr
                    listitem.get(i).setChecked(true);

                } else {
                    listitem.get(i).setChecked(true);
                }
                ///giaotiepcsdl
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private class Viewholder {
        TextView tvName, tvNumber;
        CheckBox checkBox;
    }
}
