package com.bignerdranch.android.pantryparty;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class RecipeDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recipeDB.db";
    private static final String TABLE_RECIPES = "recipes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "recipeName";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TOTAL_TIME = "totalTime";
    public static final String COLUMN_INGREDIENTS = "ingredients";
    public static final String COLUMN_INSTRUCTIONS = "instructions";
    public static final String COLUMN_SERVING_SIZE = "servingSize";

    //debugging
    private static final String TAG = "RecipeDBHandler";

    public RecipeDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE " +
                TABLE_RECIPES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RECIPE_NAME + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_TOTAL_TIME + " INTEGER," //time in minutes 
                + COLUMN_INGREDIENTS + " TEXT,"
                + COLUMN_INSTRUCTIONS + " TEXT,"
                + COLUMN_SERVING_SIZE + " INTEGER" + ")";
        db.execSQL(CREATE_RECIPES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    public void addRecipe(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, recipe.getId());
        values.put(COLUMN_RECIPE_NAME, recipe.getRecipeName());
        values.put(COLUMN_CATEGORY, recipe.getCategory());
        values.put(COLUMN_TOTAL_TIME, recipe.getTotalTime());
        values.put(COLUMN_INGREDIENTS, recipe.getIngredients());
        values.put(COLUMN_INSTRUCTIONS, recipe.getInstructions());
        values.put(COLUMN_SERVING_SIZE, recipe.getServingSize());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    /*
     * Finds a recipe in the database and returns it to the user. Users
     * can search by any of the columns in the database.
     *
     * @param input - Information the user is inputting in order to perform a search.
     *               (ex. Id number, name of the recipe, etc)
     * @param column - Which column in the database the user is searching under.
     *                 (ex. "id", "recipeName", "category", "totalTime"... etc)
     * @return Recipe - The Recipe object the user was looking for.
     *                  If NULL, then no recipe was found w/ the given input under
     *                  the selected column.
     */
    public Recipe[] findRecipe(String input, String column) {
        String columnTitle = null;
        Recipe[] recipeArray = new Recipe[30];
        if (column.equals(COLUMN_ID)) {
            columnTitle = COLUMN_ID;
        }
        else if (column.equals(COLUMN_RECIPE_NAME)) {
            columnTitle = COLUMN_RECIPE_NAME;
        }
        else if (column.equals(COLUMN_CATEGORY)) {
            columnTitle = COLUMN_CATEGORY;
        }
        else if (column.equals(COLUMN_TOTAL_TIME)) {
            columnTitle = COLUMN_TOTAL_TIME;
        }
        else if (column.equals(COLUMN_INGREDIENTS)) {
            columnTitle = COLUMN_INGREDIENTS;
        }
        else if (column.equals(COLUMN_INSTRUCTIONS)) {
            columnTitle = COLUMN_INSTRUCTIONS;
        }
        else if (column.equals(COLUMN_SERVING_SIZE)) {
            columnTitle = COLUMN_SERVING_SIZE;
        }


        String query = "Select * FROM " + TABLE_RECIPES + " WHERE " + columnTitle + " =  \"" + input + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Recipe recipe = new Recipe();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            recipe.setId(Integer.parseInt(cursor.getString(0)));
            recipe.setRecipeName(cursor.getString(1));
            recipe.setCategory(cursor.getString(2));
            recipe.setTotalTime(Integer.parseInt(cursor.getString(3)));
            recipe.setIngredients(cursor.getString(4));
            recipe.setInstructions(cursor.getString(5));
            recipe.setServingSize(Integer.parseInt(cursor.getString(6)));
            recipeArray[0] = recipe;
            int i = 1;
            while (cursor.moveToNext()) {
                //cursor.moveToNext();
                Log.d(TAG, "Cursor found another recipe");
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setRecipeName(cursor.getString(1));
                recipe.setCategory(cursor.getString(2));
                recipe.setTotalTime(Integer.parseInt(cursor.getString(3)));
                recipe.setIngredients(cursor.getString(4));
                recipe.setInstructions(cursor.getString(5));
                recipe.setServingSize(Integer.parseInt(cursor.getString(6)));
                recipeArray[i] = recipe;
                i++;
            }
            Log.d(TAG, "finished properly");
            cursor.close();
        }
        return recipeArray;
    }

    public boolean deleteRecipe(int id) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_RECIPES + " WHERE " + COLUMN_ID + " =  \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Recipe recipe = new Recipe();

        if (cursor.moveToFirst()) {
            recipe.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_RECIPES, COLUMN_ID + " = ?",
                    new String[] { Integer.toString(recipe.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

