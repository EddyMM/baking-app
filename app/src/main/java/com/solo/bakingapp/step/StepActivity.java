package com.solo.bakingapp.step;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.solo.bakingapp.R;
import com.solo.data.models.Step;


public class StepActivity extends AppCompatActivity {
    public static final String RECIPE_NAME_EXTRA = "recipe_name_extra";
    public static final String STEP_EXTRA = "step_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Intent intent = getIntent();
        if (intent.hasExtra(RECIPE_NAME_EXTRA) && intent.hasExtra(STEP_EXTRA)) {
            Step step = (Step) intent.getSerializableExtra(STEP_EXTRA);
            String recipeName = intent.getStringExtra(RECIPE_NAME_EXTRA);

            setTitle(recipeName);

            StepDetailFragment stepDetailFragment = StepDetailFragment.getInstance(step);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.step_fragment_container, stepDetailFragment)
                    .commit();
        }
    }


}
