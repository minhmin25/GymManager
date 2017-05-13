package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.fragment.ExerciseDetailFragment;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Butalo on 10/05/2017.
 */

public class ListExerciseAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Exercise> listExercise = new ArrayList<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();

    public ListExerciseAdapter() {
    }

    public ListExerciseAdapter(Activity activity, ArrayList<Exercise> listExercise) {
        this.activity = activity;
        this.listExercise = listExercise;
    }

    @Override
    public int getCount() {
        return listExercise.size();
    }

    @Override
    public Object getItem(int position) {
        return listExercise.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_list_exercise, parent, false);
            viewholder = new Viewholder();
            viewholder.iv_exercise = (ImageView) view.findViewById(R.id.iv_exercise_image);
            viewholder.tv_exercise_title = (TextView) view.findViewById(R.id.tv_exercise_title);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }

        viewholder.tv_exercise_title.setText(listExercise.get(position).getName());
        StorageReference mref = sref.child("exercise/" + listExercise.get(position).getImageUrl());
        Glide.with(activity)
                .using(new FirebaseImageLoader())
                .load(mref)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewholder.iv_exercise);

        final Exercise ex = listExercise.get(position);
        viewholder.iv_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nameExercise", ex.getName());
                ExerciseDetailFragment fragment = new ExerciseDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });

        viewholder.tv_exercise_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nameExercise", ex.getName());
                ExerciseDetailFragment fragment = new ExerciseDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }

    private class Viewholder {
        ImageView iv_exercise;
        TextView tv_exercise_title;
    }
}
