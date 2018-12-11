package com.bignerdranch.android.pantryparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchRecipeListActivity extends AppCompatActivity {

    //Recipe[] recipeList = ;
    //ArrayList<String> recipeList = new ArrayList<String>(){};
    ArrayList<String> recipeTitlesArray = new ArrayList<String>();
    ListView listView;
    //debugging
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe_list);

        Bundle extras = getIntent().getExtras();
        String[] tempArray = extras.getString("recipeTitles").split("#");

        for (int i = 0; i < tempArray.length && tempArray[i] != null; i++) {
            recipeTitlesArray.add(tempArray[i]);
            Log.d(TAG, recipeTitlesArray.get(i));
        }
        Log.d(TAG, "finished loop");

        listView = (ListView) findViewById(R.id.search_recipe_list);
        //recipeList.add("Tacos");
        //recipeList.add("Chicken Noodle Soup");
        //recipeList.add("Spaghetti with Meatballs");
        //recipeList.add("Herb Citrus Salmon");
        //recipeList.add("Chicken Parmesan");
        //recipeList.add("Cheeseburger");
        //recipeList.add("Garlic Mashed Potatoes");
        //recipeList.add("Eggs Benedict");
        //recipeList.add("Pumpkin Pancakes");
        //recipeList.add("Sesame Chicken");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, recipeTitlesArray);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchRecipeListActivity.this, recipeTitlesArray.get[position] + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchRecipeListActivity.this,TacoRecipe.class);
                startActivity(intent);
            }
        });


    }


}
