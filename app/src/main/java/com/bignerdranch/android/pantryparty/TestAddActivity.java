package com.bignerdranch.android.pantryparty;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestAddActivity extends AppCompatActivity {


    // UI references.
    private EditText mIdView;
    private EditText mRecipeNameView;
    private EditText mCategoryView;
    private EditText mTotalTimeView;
    private EditText mIngredientsView;
    private EditText mInstructionsView;
    private EditText mServingSizeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add);

        // Set up the test form.
        mIdView = (EditText) findViewById(R.id.id);
        mRecipeNameView = (EditText) findViewById(R.id.recipe_name);
        mCategoryView = (EditText) findViewById(R.id.category);
        mTotalTimeView = (EditText) findViewById(R.id.total_time);
        mIngredientsView = (EditText) findViewById(R.id.ingredients);
        mInstructionsView = (EditText) findViewById(R.id.instructions);
        mServingSizeView = (EditText) findViewById(R.id.serving_size);

        Button mTestAddButton = (Button) findViewById(R.id.test2_add_button);
        mTestAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);
            }
        });

    }

    //brings you to a page where the actual add will be used
    public void add(View view) {

        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);

        Recipe recipe =
                new Recipe(Integer.parseInt(mIdView.getText().toString()),
                        mRecipeNameView.getText().toString(),
                        mCategoryView.getText().toString(),
                        Integer.parseInt(mTotalTimeView.getText().toString()),
                        mIngredientsView.getText().toString(),
                        mInstructionsView.getText().toString(),
                        Integer.parseInt(mServingSizeView.getText().toString()));

        dbHandler.addRecipe(recipe);
    }

}

