package com.bignerdranch.android.pantryparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    //RecipeDBHandler dbHandler = new RecipeDBHandler(this, null, null, 1);
    //Recipe[] recipeList = dbHandler.getAllRecipes();
    ArrayList<String> recipeList = new ArrayList<String>(){};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        listView = (ListView) findViewById(R.id.recipe_list);

        recipeList.add("Tacos");
        recipeList.add("Chicken Noodle Soup");
        recipeList.add("Spaghetti with Meatballs");
        recipeList.add("Herb Citrus Salmon");
        recipeList.add("Chicken Parmesan");
        recipeList.add("Cheeseburger");
        recipeList.add("Garlic Mashed Potatoes");
        recipeList.add("Eggs Benedict");
        recipeList.add("Pumpkin Pancakes");
        recipeList.add("Sesame Chicken");
        recipeList.add("Fettuccine Alfredo");
        recipeList.add("Baked Ziti");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,recipeList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecipeListActivity.this, recipeList.get(position)+"", Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0:
                        Intent in = new Intent(RecipeListActivity.this,TacoRecipe.class);
                        startActivity(in);
                        break;
                    case 1:
                        in = new Intent(RecipeListActivity.this,ChickenNoodleSoup.class);
                        startActivity(in);
                        break;
                    case 2:
                        in = new Intent(RecipeListActivity.this,SpaghettiWithMeatballs.class);
                        startActivity(in);
                        break;
                    case 3:
                        in = new Intent(RecipeListActivity.this,HerbCitrusSalmon.class);
                        startActivity(in);
                        break;
                    case 4:
                        in = new Intent(RecipeListActivity.this,ChickenParmesan.class);
                        startActivity(in);
                        break;
                    case 5:
                        in = new Intent(RecipeListActivity.this,Cheeseburger.class);
                        startActivity(in);
                        break;
                    case 6:
                        in = new Intent(RecipeListActivity.this,GarlicMashedPotatoes.class);
                        startActivity(in);
                        break;
                    case 7:
                        in = new Intent(RecipeListActivity.this,EggsBenedict.class);
                        startActivity(in);
                        break;
                    case 8:
                        in = new Intent(RecipeListActivity.this,PumpkinPancakes.class);
                        startActivity(in);
                        break;
                    case 9:
                        in = new Intent(RecipeListActivity.this,SesameChicken.class);
                        startActivity(in);
                        break;
                    case 10:
                        in = new Intent(RecipeListActivity.this,FettuccineAlfredo.class);
                        startActivity(in);
                        break;
                    case 11:
                        in = new Intent(RecipeListActivity.this,BakedZiti.class);
                        startActivity(in);
                        break;



                }
            }
        });


    }


}
