package com.dev.minhmin.gymmanager.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.model.Workout;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Butalo on 12/05/2017.
 */

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    List<String> listWorkout = new ArrayList<>();
    private LinearLayout layoutBack, layoutAbs, layoutBicep, layoutTricep, layoutChest, layoutShoulder, layoutLeg;

    public static ExerciseFragment newInstance() {

        ExerciseFragment fragment = new ExerciseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).updateActionbar(ConstantUtils.TITLE_EXERCISE, false, false, false);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_exercise, container, false);
        layoutAbs = (LinearLayout) view.findViewById(R.id.layout_abdominals);
        layoutAbs.setOnClickListener(this);
        layoutBack = (LinearLayout) view.findViewById(R.id.layout_back);
        layoutBack.setOnClickListener(this);
        layoutBicep = (LinearLayout) view.findViewById(R.id.layout_biceps);
        layoutBicep.setOnClickListener(this);
        layoutTricep = (LinearLayout) view.findViewById(R.id.layout_triceps);
        layoutTricep.setOnClickListener(this);
        layoutChest = (LinearLayout) view.findViewById(R.id.layout_chest);
        layoutChest.setOnClickListener(this);
        layoutShoulder = (LinearLayout) view.findViewById(R.id.layout_shoulders);
        layoutShoulder.setOnClickListener(this);
        layoutLeg = (LinearLayout) view.findViewById(R.id.layout_legs);
        layoutLeg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        Bundle b = new Bundle();
        switch (view.getId()) {
            case R.id.layout_back: {
                b.putString("exercise", ConstantUtils.EXERCISE_BACK);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
//                ((MainActivity) getActivity()).replaceFragment(fragment);
                break;
            }
            case R.id.layout_abdominals: {
                b.putString("exercise", ConstantUtils.EXERCISE_ABS);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
            case R.id.layout_biceps: {
                b.putString("exercise", ConstantUtils.EXERCISE_BICEPS);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
            case R.id.layout_triceps: {
                b.putString("exercise", ConstantUtils.EXERCISE_TRICEPS);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
            case R.id.layout_chest: {
                b.putString("exercise", ConstantUtils.EXERCISE_CHEST);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
            case R.id.layout_shoulders: {
                b.putString("exercise", ConstantUtils.EXERCISE_SHOULDERS);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
            case R.id.layout_legs: {
                b.putString("exercise", ConstantUtils.EXERCISE_LEGS);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                replaceFragment(fragment);
                break;
            }
        }


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
