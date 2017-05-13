package com.dev.minhmin.gymmanager.model;

/**
 * Created by Administrator on 5/13/2017.
 */

public class Practice {

    private WorkoutExercise workoutExercise;
    private boolean checked;

    public Practice(WorkoutExercise workoutExercise, boolean checked) {

        this.workoutExercise = workoutExercise;
        this.checked = checked;
    }

    public Practice() {
    }


    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    public void setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
