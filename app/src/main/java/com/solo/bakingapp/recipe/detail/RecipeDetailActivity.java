package com.solo.bakingapp.recipe.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.solo.bakingapp.R;
import com.solo.bakingapp.step.StepDetailFragment;
import com.solo.data.models.Recipe;


public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        initUI();
    }

    private void initUI() {
        Intent intent = getIntent();
        if (intent.hasExtra(RecipeDetailActivity.EXTRA_RECIPE)) {
            Recipe recipe = intent.getParcelableExtra(RecipeDetailActivity.EXTRA_RECIPE);

            if (recipe != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment recipeDetailFragment = fragmentManager
                        .findFragmentById(R.id.recipe_details_fragment_container);

                // Create recipe fragment if it doesn't already exist
                if (recipeDetailFragment == null) {
                    fragmentManager.beginTransaction()
                            .add(R.id.recipe_details_fragment_container,
                                    RecipeDetailFragment.getInstance(recipe))
                            .commit();

                    setTitle(recipe.getName());
                }

                if (getResources().getBoolean(R.bool.is_tablet)) {
                    Fragment stepFragment = fragmentManager
                            .findFragmentById(R.id.step_fragment_container);

                    // Create step fragment if it doesn't already exist
                    if (stepFragment == null) {
                        fragmentManager.beginTransaction()
                                .add(R.id.step_fragment_container,
                                        StepDetailFragment.getInstance(recipe.getSteps().get(0), 0))
                                .commit();

                        setTitle(recipe.getName());
                    }
                }
            }
        }
    }
}
