package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListExerciseAdapter;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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

    ListExerciseAdapter adapter;
    ListView lv_exercise;
    private ArrayList<Exercise> listExercises = new ArrayList<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private String type = "";


    public static ListExerciseFragment newInstance() {
        ListExerciseFragment fragment = new ListExerciseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_list, container, false);
        return viewGroup;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("exercise");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_exercise = (ListView) getView().findViewById(R.id.lv_list_exercise);
        adapter = new ListExerciseAdapter(getActivity(), listExercises, type);
        lv_exercise.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Exercise").child(type).addValueEventListener(new ValueEventListener() {
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

    }


}
