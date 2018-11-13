package com.bignerdranch.android.pantryparty;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String[] recipeList = {"Taco Recipe", "Spaghetti", "Fettuccine Alfredo"};

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipeList);

        ListView theListView = (ListView) findViewById(R.id.theListView);

        //theListView.setAdapter(theAdapter);
        theListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked item: " + id + "at position: " + position);

        Intent intent = new Intent();
        intent.setClass(this, Recipe.class);
        intent.putExtra("position", position);

    }

    public void viewRecipes(View view) {
        RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
        int messageResId = 0;


    }



}
