package com.solo.data;

import android.support.annotation.NonNull;

import com.solo.data.api.RecipesApi;
import com.solo.data.api.RecipesService;
import com.solo.data.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RecipeListRepositoryData implements IRecipeListRepository {
    @Override
    public void loadRecipeList(final DelayCallback delayCallback, final SimpleIdlingResource idlingResource) {
        RecipesService recipesService = RecipesApi.getInstance();

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        Call<List<Recipe>> recipesCall = recipesService.getRecipes();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                List<Recipe> recipes = response.body();
                Timber.d("Recipes: %s", recipes);
                if (delayCallback != null) {
                    delayCallback.onDone(recipes);
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                Timber.e(t, "Problem fetching recipes");
            }
        });
    }

    public interface DelayCallback {
        void onDone(List<Recipe> recipes);
    }
}
