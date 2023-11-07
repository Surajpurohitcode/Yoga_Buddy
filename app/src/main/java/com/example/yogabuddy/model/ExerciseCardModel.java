package com.example.yogabuddy.model;

public class ExerciseCardModel {
    String exerciseTitle,exerciseDes,exerciseTime,exerciseKcal;
    int exerciseImg;

    public ExerciseCardModel(String exerciseTitle, String exerciseDes, String exerciseTime, String exerciseKcal, int exerciseImg) {
        this.exerciseTitle = exerciseTitle;
        this.exerciseDes = exerciseDes;
        this.exerciseTime = exerciseTime;
        this.exerciseKcal = exerciseKcal;
        this.exerciseImg = exerciseImg;
    }

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public String getExerciseDes() {
        return exerciseDes;
    }

    public void setExerciseDes(String exerciseDes) {
        this.exerciseDes = exerciseDes;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public String getExerciseKcal() {
        return exerciseKcal;
    }

    public void setExerciseKcal(String exerciseKcal) {
        this.exerciseKcal = exerciseKcal;
    }

    public int getExerciseImg() {
        return exerciseImg;
    }

    public void setExerciseImg(int exerciseImg) {
        this.exerciseImg = exerciseImg;
    }
}
