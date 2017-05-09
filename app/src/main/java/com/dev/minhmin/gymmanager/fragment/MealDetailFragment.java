package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.DataCenter;
import com.dev.minhmin.gymmanager.utils.MethodUtils;

/**
 * Created by Administrator on 5/6/2017.
 */

public class MealDetailFragment extends Fragment {
    private Meal meal;
    private MealDetailAdapter adapter;
    private ListView listview;

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
        setData();


    }

    private void setData() {
        DataCenter dataCenter = new DataCenter();
        Bundle bundle = this.getArguments();
        MethodUtils methodUtils = new MethodUtils();

        if (bundle != null) {
            String typeMeal = bundle.getString("typeMeal", "");
            String date = bundle.getString("date", "");
            if (typeMeal.equals("") && date.equals("")) {
                String timeNow = methodUtils.getTimeNow();
                if (timeNow.equals(date)) {
                    tv_name_day.setText("Today");
                } else {
                    tv_name_day.setText(date);
                }
                meal = dataCenter.getMeal(date, typeMeal);
                tv_total.setText(meal.getTotalCalo() + " ");
                adapter = new MealDetailAdapter(getActivity(), meal);
                listview.setAdapter(adapter);
            }

        }
    }

    private void innit() {

        tv_name_day = (TextView) getView().findViewById(R.id.tv_name_day);
        tv_total = (TextView) getView().findViewById(R.id.tv_total_calo);
        iv_back_left = (ImageView) getView().findViewById(R.id.iv_back_day);
        iv_back_right = (ImageView) getView().findViewById(R.id.iv_next_day);
        listview = (ListView) getView().findViewById(R.id.lv_food_meat);

    }


}
