package com.solo.data;

public interface IRecipeListRepository {

    void loadRecipeList(RecipeListRepositoryData.DelayCallback delayCallback, SimpleIdlingResource idlingResource);
}
