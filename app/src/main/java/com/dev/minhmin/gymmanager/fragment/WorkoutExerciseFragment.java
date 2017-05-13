package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ExpandableListworkoutAdapter;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Minh min on 5/10/2017.
 */

public class WorkoutExerciseFragment extends Fragment {

    private ArrayList<WorkoutExercise> listWorkoutExercises = new ArrayList<>();
    private String key = "0";
    //    private ListView lvWorkout;
//    private ListExerciseWorkoutAdapter adapter;
    private ExpandableListView exListview;
    private ExpandableListworkoutAdapter adapter;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> listHeader = new ArrayList<>();
    private HashMap<String, ArrayList<WorkoutExercise>> listdata = new HashMap<>();

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
        exListview = (ExpandableListView) getView().findViewById(R.id.exListview);
//        lvWorkout = (ListView) getView().findViewById(R.id.lv_workout);
        ref.child("Workout").child(key).child("listWorkoutExercise").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("ahihi", dataSnapshot.toString());
                listWorkoutExercises.clear();
                listdata.clear();
                listHeader.clear();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    WorkoutExercise w = i.getValue(WorkoutExercise.class);
                    listWorkoutExercises.add(w);
                    listHeader.add(w.getName());
                    ArrayList<WorkoutExercise> list = new ArrayList<WorkoutExercise>();
                    list.add(w);
                    listdata.put(w.getName(), list);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        adapter = new ListExerciseWorkoutAdapter(getActivity(), listWorkoutExercises);
//        lvWorkout.setAdapter(adapter);
        adapter = new ExpandableListworkoutAdapter(getActivity(), listHeader, listdata);
        exListview.setAdapter(adapter);
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
