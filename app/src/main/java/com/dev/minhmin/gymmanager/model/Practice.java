package com.dev.minhmin.gymmanager.model;

/**
 * Created by Administrator on 5/13/2017.
 */

public class Practice {

    private WorkoutExercise workoutExercise;
    private boolean checked;
    private String ref;

    public Practice(WorkoutExercise workoutExercise, boolean checked, String ref) {
        this.workoutExercise = workoutExercise;
        this.checked = checked;
        this.ref = ref;
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
