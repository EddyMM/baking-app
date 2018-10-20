package com.solo.data.api;

import com.solo.data.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesApi {
    private static RecipesService recipesService;
    private static final Object LOCK = new Object();

    public synchronized static RecipesService getInstance() {
        if (recipesService == null) {
            synchronized (LOCK) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BAKING_RECIPES_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                recipesService = retrofit.create(RecipesService.class);
            }
        }
        return recipesService;
    }

}
