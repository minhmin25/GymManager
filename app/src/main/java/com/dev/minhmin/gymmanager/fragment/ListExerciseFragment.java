package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.adapter.ListExerciseAdapter;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Minh min on 4/19/2017.
 */

public class ListExerciseFragment extends Fragment {

    private ArrayList<Exercise> listExercises = new ArrayList<>();
    private ListExerciseAdapter adapter;
    private ListView lv_exercise;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private int exercise = 0;
    private String key = "";


    public static ListExerciseFragment newInstance() {
        ListExerciseFragment fragment = new ListExerciseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).updateActionbar(ConstantUtils.TITLE_EXERCISE, false, false, false);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_list, container, false);
        lv_exercise = (ListView) viewGroup.findViewById(R.id.lv_list_exercise);
        adapter = new ListExerciseAdapter(getActivity(), listExercises);
        lv_exercise.setAdapter(adapter);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Exercise").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listExercises.clear();
                ArrayList<Exercise> listData = new ArrayList<>();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Exercise ex = i.getValue(Exercise.class);
                    listData.add(ex);
                }
                listExercises.addAll(listData);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            exercise = b.getInt("exercise");
            switch (exercise) {
                case ConstantUtils.EXERCISE_BACK: {
                    key = "Back";
                    break;
                }
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }


}
