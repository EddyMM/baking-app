package com.solo.bakingapp.recipe.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.solo.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecipeListActivity extends AppCompatActivity {

    private RecipesAdapter recipesAdapter;

    @BindView(R.id.recipes_list_recyclerview)
    RecyclerView recipesRecyclerView;

    @BindView(R.id.recipe_list_progress_bar)
    ProgressBar recipeListProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        initUI();
        setupViewModel();
    }

    private void initUI() {
        ButterKnife.bind(this);

        recipesRecyclerView.setLayoutManager(
                new GridLayoutManager(this, getResources().getInteger(R.integer.recipe_list_columns)));

        recipesAdapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }

    private void setupViewModel() {
        RecipesListViewModel recipesListViewModel = ViewModelProviders.of(this)
                .get(RecipesListViewModel.class);

        showProgressBar();
        recipesListViewModel.getRecipesLiveData().observe(this,
                (recipes) -> {
                    hideProgressBar();
                    Timber.d("Received Recipes in Activity: %s", recipes);
                    recipesAdapter.setRecipes(recipes);
                }
        );
    }

    private void showProgressBar() {
        recipeListProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        recipeListProgressBar.setVisibility(View.GONE);
    }
}
