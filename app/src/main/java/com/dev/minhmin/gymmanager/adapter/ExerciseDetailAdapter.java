package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.dev.minhmin.gymmanager.model.Workout;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Butalo on 11/05/2017.
 */

public class ExerciseDetailAdapter extends BaseAdapter {

    private Exercise exercise;
    Activity activity;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference sref = storage.getReference();
    List<String> listWorkout = new ArrayList<String>();


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        Viewholder viewholder;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.fragment_exercise_detail, parent, false);
            viewholder = new Viewholder();
            viewholder.tvTitle = (TextView) activity.findViewById(R.id.tv_exercise_title);
            viewholder.tvCalo = (TextView) activity.findViewById(R.id.tv_calo_burn);
            viewholder.tvInstruction = (TextView) activity.findViewById(R.id.tv_exercise_instruction);
            viewholder.ivExercise = (ImageView) activity.findViewById(R.id.iv_exercise_image);
            viewholder.btAdd = (Button) activity.findViewById(R.id.bt_add_exercise);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        viewholder.tvTitle.setText(exercise.getName());
        viewholder.tvCalo.setText(exercise.getCalo());
        viewholder.tvInstruction.setText(exercise.getContent());
        StorageReference mref = sref.child("text/" + exercise.getImageUrl());
        Glide.with(activity)
                .using(new FirebaseImageLoader())
                .load(mref)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewholder.ivExercise);


        viewholder.btAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("workout").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            Workout w = i.getValue(Workout.class);
                            listWorkout.add(w.getTitle());
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        View view = activity.getLayoutInflater().inflate(R.layout.fragment_exercise_detail, null);
                        builder.setView(view);
                        RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_workout_list);
                        for (String j : listWorkout) {
                            RadioButton button = new RadioButton(activity);
                            button.setText(j);
                            rg.addView(button);
                        }
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        return convertView;
    }

    private class Viewholder {
        TextView tvTitle, tvCalo, tvInstruction;
        ImageView ivExercise;
        Button btAdd;

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }
}
