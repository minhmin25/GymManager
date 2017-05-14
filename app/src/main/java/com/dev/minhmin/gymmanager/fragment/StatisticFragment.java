package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.adapter.StatisticAdapter;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.model.Practice;
import com.dev.minhmin.gymmanager.model.Statistic;
import com.dev.minhmin.gymmanager.model.WorkoutExercise;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private TextView tv_goal, tv_food, tv_excer, tv_remain, tv_breakfast, tv_lunch, tv_dinner, tv_snack, tv_excer_2, tv_food_2;
    private TextView tv_date, tv_title;
    private ImageView iv_back, iv_next;
    private String date = "";

    private ListView lvPractice;
    private ArrayList<Practice> listPractices = new ArrayList<>();
    private StatisticAdapter adapter;
    private float goal = 0;
    private Statistic statistic = new Statistic();
    private float totalExcer = 0;
    private float totalFood = 0;
    private float totalRemain = 0;
    private float totalBreak = 0, totalLunch = 0, totalDinner = 0, totalSnak = 0;
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
        Practice pr = new Practice();
        WorkoutExercise workoutExercise = new WorkoutExercise("1", "cu", 1, 1, "ref", 1, "a", false, "ref");
        pr.setChecked(false);
        pr.setWorkoutExercise(workoutExercise);
        ref.child(("listPractice")).child("14-05-2017").child(workoutExercise.getName()).setValue(pr);
        final MethodUtils methodUtils = new MethodUtils();
        date = methodUtils.getTimeNow();
//
        if (date.equals(methodUtils.getTimeNow())) {
            tv_date.setText("Today");
        } else {
            tv_date.setText(date);
        }
        updateUI();
        lvPractice = (ListView) getView().findViewById(R.id.lv_statistic_list_exercise);
        adapter = new StatisticAdapter(getActivity(), listPractices, date, this);
        lvPractice.setAdapter(adapter);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        ref.child("User").child(user.getUid()).child("goal").addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                goal = dataSnapshot.getValue(Float.class);
////                tv_goal.setText(goal + "");
//// ref.child(("Statistic")).child(date).child("totalGoal").setValue(goal);
////
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
        goal = 1000;
        ref.child(("Statistic")).child(date).child("totalGoal").setValue(goal);
        tv_goal.setText(goal + "");

//        ref.child("Statistic").child("14-05-2017").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("ahihi",dataSnapshot.toString());
//                Toast.makeText(getActivity(),dataSnapshot.toString(),Toast.LENGTH_LONG).show();
//                statistic = dataSnapshot.getValue(Statistic.class);
//                totalBreak = statistic.getTotalBreakfast();
//                totalDinner = statistic.getTotalDinner();
//                totalLunch = statistic.getTotalLunch();
//                totalSnak = statistic.getTotalSnack();
//                totalFood = totalBreak + totalDinner + totalLunch + totalSnak;
//                tv_breakfast.setText(totalBreak + "");
//                tv_lunch.setText(totalLunch + "");
//                tv_snack.setText(totalSnak + "");
//                tv_dinner.setText(totalDinner + "");
//                tv_food.setText(totalFood + "");
//           //     tv_food_2.setText(totalFood + " Calories");
//                totalRemain = goal - totalFood + totalExcer;
//                tv_remain.setText(totalRemain + "");
//                ref.child(("Statistic")).child(date).child("totalRemain").setValue(totalRemain);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        ref.child("listPractice").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPractices.clear();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Practice p = i.getValue(Practice.class);
                    listPractices.add(p);

                }
                adapter.notifyDataSetChanged();
//                tv_excer.setText(totalExcer + "");
//                tv_excer_2.setText(totalExcer + " Calories");
//                totalRemain = goal - totalFood + totalExcer;
//                tv_remain.setText(totalRemain + "");
//                ref.child(("Statistic")).child(date).child("totalRemain").setValue(totalRemain);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//
//        totalRemain = goal - totalFood + totalExcer;
//        tv_remain.setText(totalRemain + "");

    }

    private void updateUI() {
        tv_breakfast.setText(totalBreak + "");
        tv_dinner.setText(totalDinner + "");
        tv_lunch.setText(totalLunch + "");
        tv_snack.setText(totalSnak + "");
        tv_excer.setText(totalExcer + "");
        tv_excer_2.setText(totalExcer + "Calories");

        totalRemain = goal - totalFood + totalExcer;
        tv_remain.setText(totalRemain + "");
        tv_food.setText(statistic.getTotalBreakfast() + statistic.getTotalLunch() + statistic.getTotalDinner() + statistic.getTotalSnack() + "");
        //  tv_food_2.setText(statistic.getTotalBreakfast() + statistic.getTotalLunch() + statistic.getTotalDinner() + statistic.getTotalSnack() + "");

    }


    private void init() {

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
        iv_back = (ImageView) getView().findViewById(R.id.iv_plan_back_left);
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
        if (value == true) {
            totalExcer = totalExcer + (float) listPractices.get(i).getWorkoutExercise().getKalo();
        } else {
            totalExcer = totalExcer - (float) listPractices.get(i).getWorkoutExercise().getKalo();
        }
        tv_excer.setText(totalExcer + "");
        tv_excer_2.setText(totalExcer + "");


        totalRemain = goal - totalFood - totalExcer;
        tv_remain.setText(totalRemain + "");
        ref.child(("Statistic")).child(date).child("totalExercise").setValue(totalExcer);
        ref.child(("Statistic")).child(date).child("totalRemain").setValue(totalRemain);
        adapter.notifyDataSetChanged();


    }
}
