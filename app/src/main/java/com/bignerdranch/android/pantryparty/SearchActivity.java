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

public class SearchActivity extends AppCompatActivity {
    // UI references.
    private EditText mInputIngredView;
    //debugging
    private static final String TAG = "SearchActivity";

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
                searchByPantry(v);
            }
        });

    }

    /**
     * Search functionality which allows users to search by category (recipe name, category, total time, etc...)
     * Mainly used for testing database features.
     * @param view on click listener that calls the function
     */
    public void search(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
        int messageResId = 0;

        Recipe[] recipeArray =
                dbHandler.findRecipe(mInputIngredView.getText().toString().toLowerCase(), "ingredients");

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

    /**
     * Search functionality that parses through a user's pantry items input and returns to them a list
     * (starts a new activity w/ a list view) of recipes containing 1 or more of the recipes they listed.
     *
     * Use INTENT to pass the array object to the ListView Activity!! (Nerdranch Guide to Android Programming - GeoQuiz Ex)
     * @param view on click listener that calls the function
     */
    public void searchByPantry(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
        int messageResId = 0;
        String userInput = mInputIngredView.getText().toString().toLowerCase();

        //Array holding all recipes that currently exist
        Recipe[] recipeArray =
                dbHandler.getAllRecipes();

        //Array that has correct recipes to be returned in random order (ex. at index values: 0, 5, 6, 9 etc)
        Recipe[] correctRecipes = new Recipe[recipeArray.length];

        //Keeps count how many pantry items are in a recipe's ingredients
        Integer[] counter = new Integer[recipeArray.length];

        //Contains the ingredients the user entered, separated properly by commas and spaces
        String[] splitInput = userInput.split(",\\s+");

        //for each recipe we must make a list of ingredients
        for (int n = 0; n < recipeArray.length && recipeArray[n] != null; n++) {
            String[] splitRecipe = recipeArray[n].getIngredients().split(",\\s+");
            //for the # of ingredients user inputs
            for (int i = 0; i < splitInput.length; i++) {
                boolean ingredNotFound = true;
                //for the # of ingredients in each Recipe in the database
                for (int j = 0; j < splitRecipe.length && ingredNotFound; j++) {
                    if (splitInput[i].equals(splitRecipe[j])) {
                        if (correctRecipes[n] == null) {
                            correctRecipes[n] = recipeArray[n];
                            counter[n] = 0;
                        }
                        ingredNotFound = false;
                        counter[n] += 1;
                    }
                }
            }
        }

        //This is the shortened recipe list (so it lists in order (0,1,2,3) instead of random spots (0, 3, 4, 7, etc)
        Recipe[] shortCorrectRecipes = new Recipe[correctRecipes.length];
        int[] newCounter = new int[counter.length];
        int temp = 0;
        //Puts recipes in array by order of those with the most ingredients found in the recipe
        for (int k = 0; k < correctRecipes.length; k++) {
            if (correctRecipes[k] != null && counter[k] >= 4) {
                shortCorrectRecipes[temp] = correctRecipes[k];
                newCounter[temp] = counter[k];
                temp++;
            }
        }
        for (int k = 0; k < correctRecipes.length; k++) {
            if (correctRecipes[k] != null && counter[k] == 3) {
                shortCorrectRecipes[temp] = correctRecipes[k];
                newCounter[temp] = counter[k];
                temp++;
            }
        }
        for (int k = 0; k < correctRecipes.length; k++) {
            if (correctRecipes[k] != null && counter[k] == 2) {
                shortCorrectRecipes[temp] = correctRecipes[k];
                newCounter[temp] = counter[k];
                temp++;
            }
        }
        for (int k = 0; k < correctRecipes.length; k++) {
            if (correctRecipes[k] != null && counter[k] == 1) {
                shortCorrectRecipes[temp] = correctRecipes[k];
                newCounter[temp] = counter[k];
                temp++;
            }
        }

        //DEBUGGING
        //for (int k = 0; k < shortCorrectRecipes.length && shortCorrectRecipes[k] != null; k++) {
        //    Log.d(TAG, newCounter[k] + " correct ingredients in Recipe at " + k + " = " + shortCorrectRecipes[k].getRecipeName()
        //            + " and id: " + shortCorrectRecipes[k].getId());
        //}

        if (shortCorrectRecipes[0] != null) {
            messageResId = R.string.correct_toast;
            if(shortCorrectRecipes[1] != null) {
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