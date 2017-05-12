package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;

/**
 * Created by Butalo on 12/05/2017.
 */

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private LinearLayout layoutBack;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
