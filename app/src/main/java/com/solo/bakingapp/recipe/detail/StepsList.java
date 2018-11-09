package com.solo.bakingapp.recipe.detail;

import com.solo.data.models.Step;

import java.util.List;

public class StepsList {

    private static List<Step> steps;

    public static void setList(List<Step> stepsList) {
        steps = stepsList;
    }

    public static List<Step> getSteps() {
        return steps;
    }
}
