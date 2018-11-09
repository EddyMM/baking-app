package com.solo.bakingapp.step;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.solo.bakingapp.R;
import com.solo.bakingapp.recipe.detail.StepsList;
import com.solo.data.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepActivity extends AppCompatActivity {
    public static final String RECIPE_NAME_EXTRA = "recipe_name_extra";
    public static final String STEP_EXTRA = "step_extra";

    @BindView(R.id.activity_step_view_pager)
    ViewPager stepsViewPager;

    public static Intent newIntent(Context context, int stepPosition, String recipeName) {
        Intent intent = new Intent(context, StepActivity.class);
        intent.putExtra(StepActivity.STEP_EXTRA, stepPosition);
        intent.putExtra(StepActivity.RECIPE_NAME_EXTRA, recipeName);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(RECIPE_NAME_EXTRA) && intent.hasExtra(STEP_EXTRA)) {
            String recipeName = intent.getStringExtra(RECIPE_NAME_EXTRA);
            setTitle(recipeName);

            int stepPosition = intent.getIntExtra(STEP_EXTRA, 0);
            List<Step> steps = StepsList.getSteps();

            FragmentManager fragmentManager = getSupportFragmentManager();
            stepsViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                @Override
                public Fragment getItem(int position) {
                    return StepDetailFragment.getInstance(steps.get(position));
                }

                @Override
                public int getCount() {
                    return steps.size();
                }
            });

            stepsViewPager.setCurrentItem(stepPosition);
        }
    }


}
