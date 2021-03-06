package com.solo.bakingapp.recipe.list;


import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.solo.bakingapp.R;
import com.solo.bakingapp.recipe.detail.StepsList;
import com.solo.bakingapp.step.StepActivity;
import com.solo.data.models.Step;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StepDetailActivityTest {

    private Step testStep;

    @Rule
    public ActivityTestRule<StepActivity> mActivityTestRule =
            new ActivityTestRule<StepActivity>(StepActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    testStep = getTestStep();
                    ArrayList<Step> steps = new ArrayList<>();
                    StepsList.setList(steps);
                    steps.add(testStep);

                    Intent intent = new Intent();
                    intent.putExtra(StepActivity.RECIPE_NAME_EXTRA, "test_recipe");
                    intent.putExtra(StepActivity.STEP_EXTRA, 0);

                    return intent;
                }
            };


    private Step getTestStep() {
        return new Step(
                0,
                "test short desc",
                "test long desc",
                "test vid url",
                "test thumbnail url"
        );
    }

    @Test
    public void CheckStepDescriptionShown() {
        onView(withId(R.id.step_instruction_text_view))
                .check(matches(withText(testStep.getDescription())));
    }
}
