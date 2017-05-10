package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.MealDetailAdapter;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.model.LineItem;
import com.dev.minhmin.gymmanager.model.Meal;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

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
//        ArrayList<LineItem> items = new ArrayList<>();
//        Food f = new Food("baker", "Baker", "baker.png", "g", 10, 1, 2, 3, 4);
//        Food f1 = new Food("egg", "Egg", "egg.png", "g", 10, 1, 2, 3, 4);
//
//
//        LineItem lineItem = new LineItem(f, 2);
//        LineItem lineItem1 = new LineItem(f1, 3);
//
//
//        items.add(lineItem);
//        items.add(lineItem1);
        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child(typeMeal).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Meal m = i.getValue(Meal.class);
                    if (m.getDate().equals(date)) {
                        meal = m;
                        tv_total.setText(" " + meal.getTotalCalo() + " ");
                        adapter = new MealDetailAdapter(getActivity(), meal);
                        tv_total.setText(" " + meal.getTotalCalo() + " ");
                        listview.setAdapter(adapter);

                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new MealDetailAdapter(getActivity(), meal);
        tv_total.setText(" " + meal.getTotalCalo() + " ");
        listview.setAdapter(adapter);
        String timeNow = methodUtils.getTimeNow();
        if (timeNow.equals(date)) {
            tv_name_day.setText("Today");
        } else {
            tv_name_day.setText(date);
        }


        iv_back_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal = new Meal();
                tv_total.setText("");
                String time = methodUtils.UpDownDay(date, -1);
                if (time.equals(methodUtils.getTimeNow())) {
                    tv_name_day.setText("Today");

                } else {
                    tv_name_day.setText(time);

                }
                date = time;
                DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference();
                ref.child(typeMeal).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            Meal m = i.getValue(Meal.class);
                            if (m.getDate().equals(date)) {
                                meal = m;
                                adapter = new MealDetailAdapter(getActivity(), meal);
                                tv_total.setText(" " + meal.getTotalCalo() + " ");
                                listview.setAdapter(adapter);


                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                adapter = new MealDetailAdapter(getActivity(), meal);
                tv_total.setText(" " + meal.getTotalCalo() + " ");
                listview.setAdapter(adapter);

                //lay du lieu


            }
        });
        iv_back_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal = new Meal();
                tv_total.setText("");
                String time = methodUtils.UpDownDay(date, 1);
                if (time.equals(methodUtils.getTimeNow())) {
                    tv_name_day.setText("Today");

                } else {
                    tv_name_day.setText(time);

                }
                date = time;
                DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference();
                ref.child(typeMeal).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            Meal m = i.getValue(Meal.class);
                            if (m.getDate().equals(date)) {
                                meal = m;
                                adapter = new MealDetailAdapter(getActivity(), meal);
                                tv_total.setText(" " + meal.getTotalCalo() + " ");
                                listview.setAdapter(adapter);
                            }


                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                adapter = new MealDetailAdapter(getActivity(), meal);
                tv_total.setText(" " + meal.getTotalCalo() + " ");
                listview.setAdapter(adapter);

                //lay du lieu

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
        if (bundle != null) {
            typeMeal = bundle.getString("typeMeal", "");
            date = bundle.getString("date", "");
            number = bundle.getString("number", "");
            idFood = bundle.getString("idFood", "");

            ref.child("Food").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        if (i.getKey().equals(idFood)) {
                            Food f = i.getValue(Food.class);
                            LineItem l = new LineItem(f, Integer.parseInt(number));
                            meal.addLineitem(l);
//                            l.setId(ref.child(typeMeal).child(date).child("items").push().getKey());
                            ref.child(typeMeal).child(date).updateChildren(meal.toMap());
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

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
