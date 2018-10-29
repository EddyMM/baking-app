package com.solo.bakingapp.step;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solo.bakingapp.R;
import com.solo.data.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailFragment extends Fragment {
    private static final String STEP_ARG = "step_arg";

    private Step step;

    @BindView(R.id.step_instruction_text_view)
    TextView stepTextView;

    public static StepDetailFragment getInstance(Step step) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(STEP_ARG, step);

        stepDetailFragment.setArguments(args);

        return stepDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            step = (Step) args.getSerializable(STEP_ARG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step_detail, container, false);

        ButterKnife.bind(this, v);

        if (step != null) {
            stepTextView.setText(step.getDescription());
        }

        return v;
    }
}
