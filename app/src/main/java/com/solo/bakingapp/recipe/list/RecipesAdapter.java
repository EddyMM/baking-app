package com.solo.bakingapp.recipe.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.bakingapp.R;
import com.solo.bakingapp.recipe.detail.RecipeDetailActivity;
import com.solo.data.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesListViewHolder> {

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

    class RecipesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_item_name_text_view)
        TextView recipeNameTextView;
        @BindView(R.id.recipe_item_servings_text_view)
        TextView servingsTextView;
        @BindView(R.id.recipe_item_image_view)
        ImageView recipeItemImageView;

        RecipesListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        void bind(@NonNull Recipe recipe) {
            ButterKnife.bind(this, itemView);

            recipeNameTextView.setText(recipe.getName());
            servingsTextView.setText(context.getString(R.string.recipe_item_servings, recipe.getServings()));

            if (!TextUtils.isEmpty(recipe.getImage())) {
                String imageUrl = recipe.getImage();
                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.ic_baker)
                        .into(recipeItemImageView);
            }
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = recipes.get(getAdapterPosition());

            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);

            context.startActivity(intent);
        }
    }
}
