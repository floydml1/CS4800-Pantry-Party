package com.bignerdranch.android.pantryparty;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button mViewRecipeButton = (Button) findViewById(R.id.view_recipes_button);
        mViewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ViewRecipesActivity.class));
                //need ViewRecipesActivity
            }
        });

        Button mAddRecipeButton = (Button) findViewById(R.id.add_recipe_button);
        mAddRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, TestAddActivity.class));
            }
        });

        Button mSearchRecipeButton = (Button) findViewById(R.id.search_recipes_button);
        mAddRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SearchRecipesActivity.class));
                //need SearchRecipesActivity
            }
        });

        Button mFavoritesButton = (Button) findViewById(R.id.favorites_button);
        mAddRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, FavoritesActivity.class));
                //need FavoritesActivity
            }
        });


    }
}