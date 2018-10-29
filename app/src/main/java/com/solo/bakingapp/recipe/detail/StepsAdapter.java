package com.solo.bakingapp.recipe.detail;

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
import com.solo.bakingapp.step.StepActivity;
import com.solo.data.models.Step;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<Step> steps;
    private Context context;
    private String recipeName;

    StepsAdapter(@NonNull Context context, @NonNull String recipeName, @NonNull List<Step> steps) {
        this.steps = steps;
        this.context = context;
        this.recipeName = recipeName;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.step_item, viewGroup, false);
        return new StepsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, int position) {
        stepsViewHolder.bind(steps.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_label_text_view)
        TextView stepLabelTextView;

        @BindView(R.id.step_icon_image_view)
        ImageView stepIconImageView;

        StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bind(Step step, int stepNumber) {
            stepLabelTextView.setText(context.getString(
                    R.string.step_item_label,
                    String.valueOf(stepNumber),
                    step.getShortDescription())
            );

            if (!TextUtils.isEmpty(step.getThumbnailUrl())) {
                // TODO: Load video thumbnails
                Picasso.get()
                        .load(step.getThumbnailUrl())
                        .error(R.drawable.ic_mortar)
                        .into(stepIconImageView);
            }
        }

        @Override
        public void onClick(View view) {
            Step step = steps.get(getAdapterPosition());

            Intent intent = new Intent(context, StepActivity.class);
            intent.putExtra(StepActivity.STEP_EXTRA, step);
            intent.putExtra(StepActivity.RECIPE_NAME_EXTRA, recipeName);

            context.startActivity(intent);
        }
    }
}
