package com.dev.minhmin.gymmanager.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.fragment.AddFoodFragment;
import com.dev.minhmin.gymmanager.fragment.BlogDetailFragment;
import com.dev.minhmin.gymmanager.fragment.ExerciseDetailFragment;
import com.dev.minhmin.gymmanager.fragment.ExerciseFragment;
import com.dev.minhmin.gymmanager.fragment.FoodDetailFragment;
import com.dev.minhmin.gymmanager.fragment.HomeFragment;
import com.dev.minhmin.gymmanager.fragment.ListExerciseFragment;
import com.dev.minhmin.gymmanager.fragment.ListFoodFragment;
import com.dev.minhmin.gymmanager.fragment.MealDetailFragment;
import com.dev.minhmin.gymmanager.fragment.MealFragment;
import com.dev.minhmin.gymmanager.fragment.ProfileFragment;
import com.dev.minhmin.gymmanager.fragment.StatisticFragment;
import com.dev.minhmin.gymmanager.fragment.WorkoutExerciseFragment;
import com.dev.minhmin.gymmanager.fragment.WorkoutFragment;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.OnAddPressedListener;
import com.dev.minhmin.gymmanager.utils.OnBackPressedListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    public static int page = 1;
    public static int stateMain = 0;
    public static int stateWorkout = 0;
    public static int stateMeal = 0;
    public static int stateExercise = 0;
    public static int stateStatistic = 0;
    protected OnBackPressedListener onBackPressedListener;
    protected OnAddPressedListener onAddPressedListener;
    private RadioGroup bottomBar;
    private RadioButton rbHome, rbWorkout, rbMeal, rbExercise, rbStatistic;
    private TextView tvTitleActionbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ImageView ivBack, ivAdd;
    private FrameLayout layoutMain, layoutWorkout, layoutMeal, layoutExercise, layoutStatistic;

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    public void setOnAddPressedListener(OnAddPressedListener onAddPressedListener) {
        this.onAddPressedListener = onAddPressedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByID();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View customActionbar = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null);
        getSupportActionBar().setCustomView(customActionbar);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddPressedListener != null) {
                    onAddPressedListener.doAdd();
                }
            }
        });
        bottomBar.setOnCheckedChangeListener(this);
        Fragment fragment1 = new HomeFragment().newInstance();
        Fragment fragment2 = new WorkoutFragment().newInstance();
        Fragment fragment3 = new MealFragment().newInstance();
        Fragment fragment4 = new ExerciseFragment().newInstance();
        Fragment fragment5 = new StatisticFragment().newInstance();
        replaceFragment(fragment1, ConstantUtils.FRAGMENT_TAG_HOME, R.id.layout_main);
        replaceFragment(fragment2, ConstantUtils.FRAGMENT_TAG_WORKOUT, R.id.layout_workout);
        replaceFragment(fragment3, ConstantUtils.FRAGMENT_TAG_MEAL, R.id.layout_meal);
        replaceFragment(fragment4, ConstantUtils.FRAGMENT_TAG_EXERCISE, R.id.layout_exercise);
        replaceFragment(fragment5, ConstantUtils.FRAGMENT_TAG_STATISTIC, R.id.layout_statistic);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    private void findViewByID() {
        tvTitleActionbar = (TextView) findViewById(R.id.tv_title_actionbar);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        ivBack = (ImageView) findViewById(R.id.iv_actionbar_back);
        bottomBar = (RadioGroup) findViewById(R.id.bottom_bar);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbWorkout = (RadioButton) findViewById(R.id.rb_workout);
        rbMeal = (RadioButton) findViewById(R.id.rb_meal);
        rbExercise = (RadioButton) findViewById(R.id.rb_exercise);
        rbStatistic = (RadioButton) findViewById(R.id.rb_statistic);
        layoutMain = (FrameLayout) findViewById(R.id.layout_main);
        layoutWorkout = (FrameLayout) findViewById(R.id.layout_workout);
        layoutMeal = (FrameLayout) findViewById(R.id.layout_meal);
        layoutExercise = (FrameLayout) findViewById(R.id.layout_exercise);
        layoutStatistic = (FrameLayout) findViewById(R.id.layout_statistic);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        switch (page) {
            case 1: {
                switch (stateMain) {
                    case ConstantUtils.FRAGMENT_BLOG: {
                        BlogDetailFragment fragment = (BlogDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_BLOG_DETAIL);
                        fragment.doBack();
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (stateWorkout) {
                    case ConstantUtils.FRAGMENT_WORKOUT_EXERCISE: {
                        WorkoutExerciseFragment fragment = (WorkoutExerciseFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_WORKOUT_EXERCISE);
                        fragment.doBack();
                        break;
                    }
                    case ConstantUtils.FRAGMENT_EXERCISE_DETAIL: {
                        ExerciseDetailFragment fragment = (ExerciseDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_WORKOUT_EXERCISE_DETAIL);
                        fragment.doBack();
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (stateMeal) {
                    case ConstantUtils.FRAGMENT_MEAL_BREAKFAST: {
//                        MealDetailFragment fragment = (MealDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_MEAL_DETAIL);
//                        fragment.doBack();
//                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_LUNCH: {
//                        MealDetailFragment fragment = (MealDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_MEAL_DETAIL);
//                        fragment.doBack();
//                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_DINNER: {
//                        MealDetailFragment fragment = (MealDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_MEAL_DETAIL);
//                        fragment.doBack();
//                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_SNACK: {
                        MealDetailFragment fragment = (MealDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_MEAL_DETAIL);
                        fragment.doBack();
                        break;
                    }
                    case ConstantUtils.FRAGMENT_LIST_FOOD: {
                        ListFoodFragment fragment = (ListFoodFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_LIST_FOOD);
                        fragment.doBack();
                        break;
                    }
                    case ConstantUtils.FRAGMENT_ADD_FOOD: {
                        AddFoodFragment fragment = (AddFoodFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_ADD_FOOD);
                        fragment.doBack();
                        break;
                    }
                    case ConstantUtils.FRAGMENT_FOOD_DETAIL: {
                        FoodDetailFragment fragment = (FoodDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_FOOD_DETAIL);
                        fragment.doBack();
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (stateExercise) {
                    case ConstantUtils.FRAGMENT_LIST_EXERCISE: {
                        ListExerciseFragment fragment = (ListExerciseFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_LIST_EXERCISE);
                        fragment.doBack();
                        break;
                    }
                    case ConstantUtils.FRAGMENT_EXERCISE_DETAIL: {
                        ExerciseDetailFragment fragment = (ExerciseDetailFragment) getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_EXERCISE_DETAIL);
                        fragment.doBack();
                        break;
                    }
                }
                break;
            }
            default:
                super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            bottomBar.clearCheck();
            page = 5;
            updateFragment(page);
            stateStatistic = ConstantUtils.FRAGMENT_PROFILE;
            updateTitle(page, stateStatistic);
            updateActionbar(false, false);
            Fragment fragment = new ProfileFragment().newInstance();
            replaceFragment(fragment, ConstantUtils.FRAGMENT_TAG_PROFILE, R.id.layout_statistic);
        } else if (id == R.id.nav_home) {
            page = 1;
            rbHome.setChecked(true);
            updateFragment(page);
            updateTitle(page, stateMain);
        } else if (id == R.id.nav_workout) {
            page = 2;
            updateFragment(page);
            rbWorkout.setChecked(true);
            updateTitle(page, stateWorkout);
        } else if (id == R.id.nav_meal) {
            page = 3;
            updateFragment(page);
            rbMeal.setChecked(true);
            updateTitle(page, stateMeal);
        } else if (id == R.id.nav_exercise) {
            page = 4;
            updateFragment(page);
            rbExercise.setChecked(true);
            updateTitle(page, stateExercise);
        } else if (id == R.id.nav_statistic) {
            page = 5;
            rbStatistic.setChecked(true);
            updateTitle(page, stateStatistic);
        } else if (id == R.id.nav_group) {

        } else if (id == R.id.nav_map) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateFragment(int page) {
        switch (page) {
            case 1: {
                layoutMain.setVisibility(View.VISIBLE);
                layoutWorkout.setVisibility(View.INVISIBLE);
                layoutMeal.setVisibility(View.INVISIBLE);
                layoutExercise.setVisibility(View.INVISIBLE);
                layoutStatistic.setVisibility(View.INVISIBLE);
                break;
            }
            case 2: {
                layoutMain.setVisibility(View.INVISIBLE);
                layoutWorkout.setVisibility(View.VISIBLE);
                layoutMeal.setVisibility(View.INVISIBLE);
                layoutExercise.setVisibility(View.INVISIBLE);
                layoutStatistic.setVisibility(View.INVISIBLE);
                break;
            }
            case 3: {
                layoutMain.setVisibility(View.INVISIBLE);
                layoutWorkout.setVisibility(View.INVISIBLE);
                layoutMeal.setVisibility(View.VISIBLE);
                layoutExercise.setVisibility(View.INVISIBLE);
                layoutStatistic.setVisibility(View.INVISIBLE);
                break;
            }
            case 4: {
                layoutMain.setVisibility(View.INVISIBLE);
                layoutWorkout.setVisibility(View.INVISIBLE);
                layoutMeal.setVisibility(View.INVISIBLE);
                layoutExercise.setVisibility(View.VISIBLE);
                layoutStatistic.setVisibility(View.INVISIBLE);
                break;
            }
            case 5: {
                layoutMain.setVisibility(View.INVISIBLE);
                layoutWorkout.setVisibility(View.INVISIBLE);
                layoutMeal.setVisibility(View.INVISIBLE);
                layoutExercise.setVisibility(View.INVISIBLE);
                layoutStatistic.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_home: {
                if (page == 1) break;
                page = 1;
                updateFragment(page);
                updateTitle(page, stateMain);
                navigationView.setCheckedItem(R.id.nav_home);
                break;
            }
            case R.id.rb_workout: {
                if (page == 2) break;
                page = 2;
                updateFragment(page);
                updateTitle(page, stateWorkout);
                navigationView.setCheckedItem(R.id.nav_workout);
                break;
            }
            case R.id.rb_meal: {
                if (page == 3) break;
                page = 3;
                updateFragment(page);
                updateTitle(page, stateMeal);
                navigationView.setCheckedItem(R.id.nav_meal);
                break;
            }
            case R.id.rb_exercise: {
                if (page == 4) break;
                page = 4;
                updateFragment(page);
                updateTitle(page, stateExercise);
                navigationView.setCheckedItem(R.id.nav_exercise);
                break;
            }
            case R.id.rb_statistic: {
                if (page == 5) break;
                page = 5;
                stateStatistic = ConstantUtils.FRAGMENT_STATISTIC;
                updateFragment(page);
                updateTitle(page, stateStatistic);
                Fragment fragment = new StatisticFragment().newInstance();
                replaceFragment(fragment, ConstantUtils.FRAGMENT_TAG_STATISTIC, R.id.layout_statistic);
                navigationView.setCheckedItem(R.id.nav_statistic);
                break;
            }
        }
    }

    public void replaceFragment(Fragment fragment, String TAG) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.layout_main, fragment, TAG);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment, String TAG, int id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(id, fragment, TAG);
        ft.commit();
    }

    public void updateActionbar(boolean isShowBack, boolean isShowAdd) {
        if (isShowBack) {
            toggle.setDrawerIndicatorEnabled(false);
            ivBack.setVisibility(View.VISIBLE);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
            ivBack.setVisibility(View.GONE);
        }
        if (isShowAdd) {
            ivAdd.setVisibility(View.VISIBLE);
        } else {
            ivAdd.setVisibility(View.INVISIBLE);
        }

    }

    public void updateTitle(int page, int state) {
        switch (page) {
            case 1: {
                switch (stateMain) {
                    case ConstantUtils.FRAGMENT_HOME: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_HOME);
                        updateActionbar(false, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_BLOG: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_BLOG);
                        updateActionbar(true, false);
                        break;
                    }
                    case 0: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_HOME);
                        updateActionbar(false, false);
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (stateWorkout) {
                    case ConstantUtils.FRAGMENT_WORKOUT: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_WORKOUT);
                        updateActionbar(false, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_WORKOUT_EXERCISE: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_WORKOUT);
                        updateActionbar(true, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_EXERCISE_DETAIL: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE_DETAIL);
                        updateActionbar(true, false);
                        break;
                    }
                    case 0: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_WORKOUT);
                        updateActionbar(false, false);
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (stateMeal) {
                    case ConstantUtils.FRAGMENT_MEAL: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_MEAL);
                        updateActionbar(false, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_LIST_FOOD: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_FOOD);
                        updateActionbar(true, true);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_FOOD_DETAIL: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_FOOD_DETAIL);
                        updateActionbar(true, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_ADD_FOOD: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_FOOD);
                        updateActionbar(true, true);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_BREAKFAST: {
                        tvTitleActionbar.setText(ConstantUtils.Breakfast);
                        updateActionbar(true, true);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_LUNCH: {
                        tvTitleActionbar.setText(ConstantUtils.Lunch);
                        updateActionbar(true, true);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_DINNER: {
                        tvTitleActionbar.setText(ConstantUtils.Dinner);
                        updateActionbar(true, true);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_MEAL_SNACK: {
                        tvTitleActionbar.setText(ConstantUtils.Snack);
                        updateActionbar(true, true);
                        break;
                    }
                    case 0: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_MEAL);
                        updateActionbar(false, false);
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (stateExercise) {
                    case ConstantUtils.FRAGMENT_EXERCISE: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE);
                        updateActionbar(false, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_LIST_EXERCISE: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE);
                        updateActionbar(true, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_EXERCISE_DETAIL: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE_DETAIL);
                        updateActionbar(true, false);
                        break;
                    }
                    case 0: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_EXERCISE);
                        updateActionbar(false, false);
                        break;
                    }
                }
                break;
            }
            case 5: {
                switch (state) {
                    case ConstantUtils.FRAGMENT_PROFILE: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_PROFILE);
                        updateActionbar(false, false);
                        break;
                    }
                    case ConstantUtils.FRAGMENT_STATISTIC: {
                        tvTitleActionbar.setText(ConstantUtils.TITLE_STATISTIC);
                        updateActionbar(false, false);
                        break;
                    }
                }
                break;
            }
        }
    }

}
