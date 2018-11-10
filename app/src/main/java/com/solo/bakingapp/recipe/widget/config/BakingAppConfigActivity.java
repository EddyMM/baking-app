package com.solo.bakingapp.recipe.widget.config;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

import com.solo.bakingapp.R;
import com.solo.bakingapp.recipe.list.RecipesListViewModel;
import com.solo.data.models.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;

public class BakingAppConfigActivity extends AppCompatActivity implements RecipeSelectionAdapter.RecipeSelectionListener {
    private RecipeSelectionAdapter recipeSelectionAdapter;

    @BindView(R.id.recipe_selection_progress_bar)
    ProgressBar recipeSelectionProgressBar;

    @BindView(R.id.recipes_selection_recyclerview)
    RecyclerView recipeSelectionRecyclerView;
    private int widgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selection);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            widgetId = extras.getInt(EXTRA_APPWIDGET_ID);
        }

        initUI();
        setupViewModel();
    }

    private void initUI() {
        ButterKnife.bind(this);

        recipeSelectionAdapter = new RecipeSelectionAdapter(this, this);

        recipeSelectionRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recipeSelectionRecyclerView.setAdapter(recipeSelectionAdapter);
    }

    private void setupViewModel() {
        RecipesListViewModel recipesListViewModel = ViewModelProviders.of(this)
                .get(RecipesListViewModel.class);

        showProgressBar();
        recipesListViewModel.getRecipesLiveData().observe(this,
                (recipes) -> {
                    hideProgressBar();
                    Timber.d("Received Recipes in Activity: %s", recipes);
                    recipeSelectionAdapter.setRecipes(recipes);
                }
        );
    }

    private void showProgressBar() {
        recipeSelectionProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        recipeSelectionProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRecipeSelected(List<Ingredient> ingredientList) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.baking_app_widget);
        StringBuilder ingredientsStringBuilder = new StringBuilder();
        for (Ingredient ingredient : ingredientList) {
            ingredientsStringBuilder.append(
                    String.format("- %s (%s %s)\n",
                            ingredient.getName().substring(0, 1).toUpperCase() +
                                    ingredient.getName().substring(1),
                            ingredient.getQuantity(),
                            ingredient.getMeasure())
            );
        }
        views.setTextViewText(R.id.appwidget_text, ingredientsStringBuilder.toString());

        AppWidgetManager.getInstance(this).updateAppWidget(widgetId, views);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_APPWIDGET_ID, widgetId);
        setResult(RESULT_OK, intent);

        finish();
    }
}
