package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.LineItem;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.DataCenter;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/6/2017.
 */

public class MealDetailFragment extends Fragment {
    private Meal meal = new Meal();
    private MealDetailAdapter adapter;
    private ListView listview;
    private TextView tv_total, tv_name_day;
    private ImageView iv_back_left, iv_back_right;
    private ImageView iv_add_food;
    private String date = "";
    private String typeMeal = "";
    private String idFood = "";
    private String number = "";
    private TextView tv_title;

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
        tv_title = (TextView) ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);
        tv_title.setText(typeMeal.toString());
        View view = ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView();
        iv_add_food = (ImageView) view.findViewById(R.id.iv_add);

        final MethodUtils methodUtils = new MethodUtils();
        ArrayList<LineItem> items = new ArrayList<>();
        Food f = new Food("baker", "Baker", "baker.png", "g", 10, 1, 2, 3, 4);
        Food f1 = new Food("egg", "Egg", "egg.png", "g", 10, 1, 2, 3, 4);
        if (!idFood.equals("") && !number.equals("")) {

            Food f2 = new Food(idFood, "A", "egg.png", "g", 10, 1, 1, 1, 1);
            LineItem lineItem2 = new LineItem(f2, Integer.parseInt(number));
            items.add(lineItem2);

        }

        LineItem lineItem = new LineItem(f, 2);
        LineItem lineItem1 = new LineItem(f1, 3);


        items.add(lineItem);
        items.add(lineItem1);


        Meal meal_1 = new Meal("10-05-2017", typeMeal, items, "10-05-2017");

        meal = meal_1;

        String timeNow = methodUtils.getTimeNow();
        if (timeNow.equals(date)) {
            tv_name_day.setText("Today");
        } else {
            tv_name_day.setText(date);
        }
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        ref.child(typeMeal).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Meal m = new Meal();
//                for (DataSnapshot i : dataSnapshot.getChildren()) {
//                    Log.e("ahihi", i.toString());
//                    if (i.getKey().equals(date)) {
//                        Log.e("a", "a");
////                        m = i.getValue(Meal.class);
//                    }
//                    // sai day ak chăc thế, nó k lấy đk
////                            Meal m = i.getValue(Meal.class);
////                            if (m.getDate().equals(date)) {
////
////
////                            }
//                }
//                meal = m;
//                tv_total.setText(meal.getTotalCalo() + " ");
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        tv_total.setText(" " + meal.getTotalCalo() + " ");
        adapter = new MealDetailAdapter(getActivity(), meal);
        listview.setAdapter(adapter);
        iv_back_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = methodUtils.UpDownDay(date, -1);
                if (time.equals(methodUtils.getTimeNow())) {
                    tv_name_day.setText("Today");

                } else {
                    tv_name_day.setText(time);

                }
                date = time;
                //lay du lieu


            }
        });
        iv_back_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = methodUtils.UpDownDay(date, 1);
                if (time.equals(methodUtils.getTimeNow())) {
                    tv_name_day.setText("Today");

                } else {
                    tv_name_day.setText(time);

                }
                date = time;
            }
        });
        iv_add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typeMeal", typeMeal);
                bundle.putString("date", date);
                ListFoodFragment fragment = new ListFoodFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = this.getArguments();
        typeMeal = bundle.getString("typeMeal", "");


        date = bundle.getString("date", "");
        number = bundle.getString("number", "");
        idFood = bundle.getString("idFood", "");


//        String timeNow = methodUtils.getTimeNow();
//            if (timeNow.equals(date)) {
//                tv_name_day.setText("Today");
//            } else {
//                tv_name_day.setText(date);
//            }

//        if (bundle != null) {
//            String typeMeal = bundle.getString("typeMeal", "");
//            final String date = bundle.getString("date", "");
//
//            String timeNow = methodUtils.getTimeNow();
//            if (timeNow.equals(date)) {
//                tv_name_day.setText("Today");
//            } else {
//                tv_name_day.setText(date);
//            }
//
//
////            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
////            ref.child(typeMeal).addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot dataSnapshot) {
////                    for (DataSnapshot i : dataSnapshot.getChildren()) {
////                        if (i.getKey().equals(date)) {
////                            meal = i.getValue(Meal.class);
////                            adapter.notifyDataSetChanged();
////                        }
//////                            Meal m = i.getValue(Meal.class);
//////                            if (m.getDate().equals(date)) {
//////
//////
//////                            }
////                    }
////                }
////
////                @Override
////                public void onCancelled(DatabaseError databaseError) {
////
////                }
////            });
////
////
//
//      }

    }

    private void innit() {

        tv_name_day = (TextView) getView().findViewById(R.id.tv_name_day);
        tv_total = (TextView) getView().findViewById(R.id.tv_total_calo);
        iv_back_left = (ImageView) getView().findViewById(R.id.iv_back_day);
        iv_back_right = (ImageView) getView().findViewById(R.id.iv_next_day);
        listview = (ListView) getView().findViewById(R.id.lv_food_meat);


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }



}
