package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/10/2017.
 */

public class WorkoutExerciseFragment extends Fragment {

    private ArrayList<WorkoutExercise> listWorkoutExercises = new ArrayList<>();
    private String key;

    public static WorkoutExerciseFragment newInstance() {
        WorkoutExerciseFragment fragment = new WorkoutExerciseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_workout_exercise, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            
        }
    }
}
