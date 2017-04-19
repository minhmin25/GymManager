package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListWorkoutAdapter;
import com.dev.minhmin.gymmanager.model.Workout;

import java.util.ArrayList;

/**
 * Created by Minh min on 4/19/2017.
 */

public class WorkoutFragment extends Fragment {

    private GridView gvWorkout;
    private ArrayList<Workout> listWorkouts = new ArrayList<>();
    private ListWorkoutAdapter adapter;

    public static WorkoutFragment newInstance() {
        WorkoutFragment fragment = new WorkoutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_workout, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gvWorkout = (GridView) getView().findViewById(R.id.gv_workout);
        getListData();
        adapter = new ListWorkoutAdapter(getActivity(), listWorkouts);
        gvWorkout.setAdapter(adapter);

    }

    private void getListData() {
        for (int i = 0; i < 7; i++) {
            Workout w = new Workout("Title " + i, i + 3, "Url " + i);
            listWorkouts.add(w);
        }
    }
}
