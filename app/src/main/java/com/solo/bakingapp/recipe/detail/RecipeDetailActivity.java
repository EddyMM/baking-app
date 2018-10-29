package com.solo.bakingapp.recipe.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.solo.bakingapp.R;
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
            Recipe recipe = (Recipe) intent.getSerializableExtra(RecipeDetailActivity.EXTRA_RECIPE);

            if (recipe != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment fragment = fragmentManager
                        .findFragmentById(R.id.recipe_details_fragment_container);

                // Create fragment if it doesn't already exist
                if (fragment == null) {
                    fragmentManager.beginTransaction()
                            .add(R.id.recipe_details_fragment_container,
                                    RecipeDetailFragment.getInstance(recipe))
                            .commit();

                    setTitle(recipe.getName());
                }
            }
        }
    }
}
