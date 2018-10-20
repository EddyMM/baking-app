package com.solo.bakingapp.recipe;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.solo.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecipeListActivity extends AppCompatActivity {

    private RecipesAdapter recipesAdapter;

    @BindView(R.id.recipes_list_recyclerview)
    RecyclerView recipesRecyclerView;

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

        recipesListViewModel.getRecipesLiveData().observe(this,
                (recipes) -> {
                    Timber.d("Received Recipes in Activity: %s", recipes);
                    recipesAdapter.setRecipes(recipes);
                }
        );
    }
}
