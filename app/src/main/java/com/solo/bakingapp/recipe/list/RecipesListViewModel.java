package com.solo.bakingapp.recipe.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.solo.data.RecipeListRepositoryData;
import com.solo.data.SimpleIdlingResource;
import com.solo.data.models.Recipe;

import java.util.List;

public class RecipesListViewModel extends AndroidViewModel implements RecipeListRepositoryData.DelayCallback {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
    @Nullable
    private SimpleIdlingResource simpleIdlingResource;

    public RecipesListViewModel(@NonNull Application application) {
        super(application);

        getIdlingResource();
        fetchRecipes();
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if (null == simpleIdlingResource) {
            simpleIdlingResource = new SimpleIdlingResource();
        }

        return simpleIdlingResource;
    }

    public LiveData<List<Recipe>> getRecipesLiveData() {
        return recipesLiveData;
    }

    private void fetchRecipes() {
        RecipeListRepositoryData recipeListRepositoryData = new RecipeListRepositoryData();
        recipeListRepositoryData.loadRecipeList(this, simpleIdlingResource);
    }

    @Override
    public void onDone(List<Recipe> recipes) {
        recipesLiveData.setValue(recipes);
    }
}
