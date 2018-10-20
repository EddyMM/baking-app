package com.solo.bakingapp.recipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.bakingapp.R;
import com.solo.data.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesListViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    RecipesAdapter(Context context) {
        this.context = context;
    }

    void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipesListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_item, viewGroup, false);
        return new RecipesListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesListViewHolder recipesListViewHolder, int position) {
        recipesListViewHolder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }

    class RecipesListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipeItemNameTextView) TextView recipeNameTextView;
        @BindView(R.id.recipeItemServingsTextView) TextView servingsTextView;
        @BindView(R.id.recipeItemImageView) ImageView recipeItemImageView;

        RecipesListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(@NonNull Recipe recipe) {
            ButterKnife.bind(this, itemView);

            recipeNameTextView.setText(recipe.getName());
            servingsTextView.setText(context.getString(R.string.recipe_item_servings, recipe.getServings()));

            if(!TextUtils.isEmpty(recipe.getImage())) {
                String imageUrl = recipe.getImage();
                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.ic_baker)
                        .into(recipeItemImageView);
            }
        }
    }
}
