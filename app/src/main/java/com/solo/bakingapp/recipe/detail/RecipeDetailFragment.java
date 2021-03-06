package com.solo.bakingapp.recipe.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.bakingapp.R;
import com.solo.bakingapp.step.StepActivity;
import com.solo.bakingapp.step.StepDetailFragment;
import com.solo.data.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeDetailFragment extends Fragment implements StepsAdapter.StepListener {
    private static final String RECIPE_ARG = "recipe_arg";

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRecyclerView;

    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;

    private Recipe recipe;


    public static RecipeDetailFragment getInstance(Recipe recipe) {
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_ARG, recipe);

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(args);

        return recipeDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            recipe = args.getParcelable(RECIPE_ARG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager ingredientsLinearLayoutManager = getLayoutManager(requireContext());
        DividerItemDecoration ingredientsDividerItemDecoration =
                getDividerItemDecoration(ingredientsLinearLayoutManager);

        ingredientsRecyclerView.setLayoutManager(ingredientsLinearLayoutManager);
        ingredientsRecyclerView.setAdapter(new IngredientsAdapter(requireContext(), recipe.getIngredients()));
        ingredientsRecyclerView.addItemDecoration(ingredientsDividerItemDecoration);
        ingredientsRecyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager stepsLinearLayoutManager = getLayoutManager(requireContext());
        DividerItemDecoration stepsDividerItemDecoration =
                getDividerItemDecoration(ingredientsLinearLayoutManager);

        stepsRecyclerView.setLayoutManager(stepsLinearLayoutManager);
        stepsRecyclerView.setAdapter(new StepsAdapter(
                requireContext(),
                recipe.getSteps(),
                this));
        stepsRecyclerView.addItemDecoration(stepsDividerItemDecoration);
        stepsRecyclerView.setNestedScrollingEnabled(false);


        return view;
    }

    private LinearLayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    private DividerItemDecoration getDividerItemDecoration(LinearLayoutManager linearLayoutManager) {
        return new DividerItemDecoration(
                requireContext(), linearLayoutManager.getOrientation());
    }

    @Override
    public void onStepClick(int stepPosition) {
        if (requireContext().getResources().getBoolean(R.bool.is_tablet)) {
            // Load step into fragment
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            StepDetailFragment stepFragment = StepDetailFragment
                    .getInstance(recipe.getSteps().get(stepPosition), stepPosition);

            fragmentManager.beginTransaction()
                    .replace(R.id.step_fragment_container, stepFragment)
                    .commit();
        } else {
            Intent intent = StepActivity.newIntent(requireContext(), stepPosition, recipe.getName());
            StepsList.setList(recipe.getSteps());
            requireContext().startActivity(intent);
        }
    }
}
