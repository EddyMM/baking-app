package com.solo.bakingapp.recipe;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.solo.data.api.RecipesApi;
import com.solo.data.api.RecipesService;
import com.solo.data.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RecipesListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();

    public RecipesListViewModel(@NonNull Application application) {
        super(application);

        fetchRecipes();
    }

    LiveData<List<Recipe>> getRecipesLiveData() {
        return recipesLiveData;
    }

    private void fetchRecipes() {
        RecipesService recipesService = RecipesApi.getInstance();
        Call<List<Recipe>> recipesCall = recipesService.getRecipes();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                List<Recipe> recipes = response.body();
                Timber.d("Recipes: %s", recipes);
                recipesLiveData.setValue(recipes);
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                Timber.e(t, "Problem fetching recipes");
            }
        });
    }


}
