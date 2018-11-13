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

public class SearchActivity extends AppCompatActivity {
    // UI references.
    private EditText mInputIngredView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set up the test form.
        mInputIngredView = (EditText) findViewById(R.id.add_pantry_item_box);

        Button mTestSearchButton = (Button) findViewById(R.id.test_search_button);
        mTestSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });

    }

    public void search(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
        int messageResId = 0;

        Recipe[] recipeArray =
                dbHandler.findRecipe(mInputIngredView.getText().toString(), "ingredients");

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
}