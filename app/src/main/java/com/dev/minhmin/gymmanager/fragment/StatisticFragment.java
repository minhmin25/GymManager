package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.adapter.StatisticAdapter;
import com.dev.minhmin.gymmanager.model.Practice;
import com.dev.minhmin.gymmanager.model.Statistic;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/13/2017.
 */

public class StatisticFragment extends Fragment implements StatisticAdapter.onCheckedChangeListener {
    private TextView tv_weight, tv_height, tv_goal, tv_food, tv_excer, tv_remain, tv_breakfast, tv_lunch, tv_dinner, tv_snack, tv_excer_2;
    private TextView tv_date, tv_title;
    private ImageView iv_back, iv_next;
    private String date = "";
    private String weight = "", height = "";
    private ListView lvPractice;
    private ArrayList<Practice> listPractices = new ArrayList<>();
    private StatisticAdapter adapter;
    private Statistic statistic = new Statistic();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public static StatisticFragment newInstance() {
        StatisticFragment fragment = new StatisticFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_statistic, container, false);
        ((MainActivity) getActivity()).updateActionbar(ConstantUtils.TITLE_STATISTIC, false, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        lvPractice = (ListView) getView().findViewById(R.id.lv_statistic_list_exercise);
        MethodUtils methodUtils = new MethodUtils();
        date = methodUtils.getTimeNow();
        tv_date.setText(date);
        adapter = new StatisticAdapter(getActivity(), listPractices, date, this);
        lvPractice.setAdapter(adapter);
        ref.child("Statistic").child(date).child("listPractice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPractices.clear();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Practice p = i.getValue(Practice.class);
                    listPractices.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        tv_weight = (TextView) getView().findViewById(R.id.tv_plan_weight);
        tv_height = (TextView) getView().findViewById(R.id.tv_plan_height);
        tv_goal = (TextView) getView().findViewById(R.id.tv_plan_goal);
        tv_food = (TextView) getView().findViewById(R.id.tv_plan_food);
        tv_excer = (TextView) getView().findViewById(R.id.tv_plan_excer);
        tv_breakfast = (TextView) getView().findViewById(R.id.tv_plan_break);
        tv_dinner = (TextView) getView().findViewById(R.id.tv_plan_dinner);
        tv_lunch = (TextView) getView().findViewById(R.id.tv_plan_lunch);
        tv_snack = (TextView) getView().findViewById(R.id.tv_plan_snak);
        tv_excer_2 = (TextView) getView().findViewById(R.id.tv_plan_excer_2);
        tv_remain = (TextView) getView().findViewById(R.id.tv_plan_remain);
        tv_date = (TextView) getView().findViewById(R.id.tv_plan_date);
        iv_back = (ImageView) getView().findViewById(R.id.iv__plan_back_left);
        iv_next = (ImageView) getView().findViewById(R.id.iv_plan_back_right);
        lvPractice = (ListView) getView().findViewById(R.id.lv_statistic_list_exercise);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }

    @Override
    public void calculate(int i, boolean value) {
        listPractices.get(i).setChecked(value);
    }
}
