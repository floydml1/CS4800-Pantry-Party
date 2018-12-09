package com.bignerdranch.android.pantryparty;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";

    // UI references.
    private EditText mInputView;
    private EditText mColumnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Set up the test form.
        mInputView = (EditText) findViewById(R.id.input);

        mColumnView = (EditText) findViewById(R.id.column);

        Button mTestAddButton = (Button) findViewById(R.id.test_add_button);
        mTestAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);

            }
        });


        Button mTestSearchButton = (Button) findViewById(R.id.test_search_button);
        mTestSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });

        Button mTestRemoveButton = (Button) findViewById(R.id.test_remove_button);
        mTestRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(v);
            }
        });
    }

    //brings you to a page where the actual add will be used
    public void add(View view) {
        startActivity(new Intent(TestActivity.this, TestAddActivity.class));


        /*
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);

        String column =
                mColumnView.getText().toString();

        Recipe recipe =
                new Recipe();

        dbHandler.addRecipe(recipe);
        mInputView.setText("");
        mColumnView.setText("");
        */
    }

    public void search(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
        int messageResId = 0;

        Recipe[] recipeArray =
                dbHandler.findRecipe(mInputView.getText().toString().toLowerCase(), mColumnView.getText().toString());

        for (int m = 0; m < recipeArray.length && recipeArray[m] != null; m++) {
            //String[] splitRecipe = recipeArray[m].getIngredients().split(",\\s+");
            Log.d(TAG, "Array value at " + m + " = " + recipeArray[m].getRecipeName()
                    + " and id: " + recipeArray[m].getId());
        }

        if (recipeArray[0] != null) {
            messageResId = R.string.correct_toast;
            if(recipeArray[1] != null) {
                messageResId = R.string.multiple_correct_toast;
            }
        }
        else
        {
            messageResId = R.string.incorrect_toast;
        }
        Toast toastTrue = Toast.makeText(this, messageResId,
                Toast.LENGTH_SHORT);
        toastTrue.setGravity(Gravity.TOP, 0, 0);
        toastTrue.show();
    }

    public void remove(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null,
                null, 1);
        int messageResId = 0;

        if ((mColumnView.getText().toString()).equals("id")) {
            boolean result = dbHandler.deleteRecipe(
                    Integer.parseInt(mInputView.getText().toString()));

            if (result) {
                messageResId = R.string.correct_delete;
                mInputView.setText("");
                mColumnView.setText("");
            } else
                messageResId = R.string.incorrect_toast;
        }
        else {
            messageResId = R.string.wrong_format_toast;
        }
        Toast toastTrue = Toast.makeText(this, messageResId,
                Toast.LENGTH_SHORT);
        toastTrue.setGravity(Gravity.TOP, 0, 0);
        toastTrue.show();

    }


}

