package com.dev.minhmin.gymmanager.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Workout;
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

    private LinearLayout layoutBack;
    List<String> listWorkout = new ArrayList<>();

    public static ExerciseFragment newInstance() {

        ExerciseFragment fragment = new ExerciseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).updateActionbar(ConstantUtils.TITLE_EXERCISE, false, false, false);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_exercise, container, false);
        layoutBack = (LinearLayout) view.findViewById(R.id.layout_back);
        layoutBack.setOnClickListener(this);
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
                b.putInt("exercise", ConstantUtils.EXERCISE_BACK);
                Fragment fragment = new ListExerciseFragment().newInstance();
                fragment.setArguments(b);
                ((MainActivity) getActivity()).replaceFragment(fragment);
                break;
            }
        }

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (position) {
//            case 0:
//                replaceFragment(ConstantUtils.abs);
//                break;
//            case 1:
//                replaceFragment(ConstantUtils.back);
//                break;
//            case 2:
//                replaceFragment(ConstantUtils.biceps);
//                break;
//            case 3:
//                replaceFragment(ConstantUtils.triceps);
//                break;
//            case 4:
//                replaceFragment(ConstantUtils.chest);
//                break;
//            case 5:
//                replaceFragment(ConstantUtils.shoulders);
//                break;
//            case 6:
//                replaceFragment(ConstantUtils.legs);
//                break;
//
//        }
//    }
}
