package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListExerciseWorkoutAdapter;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/10/2017.
 */

public class WorkoutExerciseFragment extends Fragment {

    private ArrayList<WorkoutExercise> listWorkoutExercises = new ArrayList<>();
    private String key;
    private ExpandableListView elvWorkout;
    private ListExerciseWorkoutAdapter adapter;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

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
        elvWorkout = (ExpandableListView) getActivity().findViewById(R.id.elv_workout);
        ref.child("Workout").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listWorkoutExercises.clear();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    listWorkoutExercises.add(i.getValue(WorkoutExercise.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new ListExerciseWorkoutAdapter(getActivity(), listWorkoutExercises);
        elvWorkout.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString("key");
        }
    }

}
