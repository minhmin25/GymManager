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

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Workout;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Butalo on 12/05/2017.
 */

public class ExerciseFragment extends Fragment implements AdapterView.OnItemClickListener {
    List<String> listWorkout = new ArrayList<>();

    public static ExerciseFragment newInstance() {

        Bundle args = new Bundle();
        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        ref.child("workout").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot i : dataSnapshot.getChildren()) {
//                    Workout w = i.getValue(Workout.class);
//                    listWorkout.add(w.getTitle());
//                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//                View view = inflater.inflate(R.layout.fragment_exercise_detail, null);
//
//                builder.setView(view);
//                RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_workout_list);
//                for (String j: listWorkout){
//                    RadioButton button = new RadioButton(getActivity());
//                    button.setText(j);
//                    rg.addView(button);
//                }
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                })
//                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    private void replaceFragment(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ListExerciseFragment fragment = new ListExerciseFragment();
        fragment.setArguments(bundle);

        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                replaceFragment(ConstantUtils.abs);
                break;
            case 1:
                replaceFragment(ConstantUtils.back);
                break;
            case 2:
                replaceFragment(ConstantUtils.biceps);
                break;
            case 3:
                replaceFragment(ConstantUtils.triceps);
                break;
            case 4:
                replaceFragment(ConstantUtils.chest);
                break;
            case 5:
                replaceFragment(ConstantUtils.shoulders);
                break;
            case 6:
                replaceFragment(ConstantUtils.legs);
                break;

        }
    }
}
