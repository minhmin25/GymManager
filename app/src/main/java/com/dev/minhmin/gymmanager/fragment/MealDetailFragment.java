package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Meal;

/**
 * Created by Administrator on 5/6/2017.
 */

public class MealDetailFragment extends Fragment {
    private Meal meal;
    private MealDetailAdapter adapter;
    private ListView listitem;
    private TextView tv_total, tv_name_day;
    private ImageView iv_back_left, iv_back_right;


    public static MealDetailFragment newInstance() {
        MealDetailFragment fragment = new MealDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_meal_detail, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        innit();

    }

    private void innit() {

    }


}
