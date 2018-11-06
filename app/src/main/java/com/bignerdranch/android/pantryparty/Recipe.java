package com.bignerdranch.android.pantryparty;

public class Recipe {
    private int mId;
    private String mRecipeName;
    private String mCategory;
    private int mTotalTime;
    private String mIngredients;
    private String mInstructions;
    private int mServingSize;

    public Recipe() {

    }

    public Recipe(int id, String recipeName, String category, int totalTime, String ingredients,
                String instructions, int servingSize) {
        mId = id;
        mRecipeName = recipeName;
        mCategory = category;
        mTotalTime = totalTime;
        mIngredients = ingredients;
        mInstructions = instructions;
        mServingSize = servingSize;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public void setRecipeName(String recipeName) {
        mRecipeName = recipeName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public int getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public void setIngredients(String ingredients) {
        mIngredients = ingredients;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    public int getServingSize() {
        return mServingSize;
    }

    public void setServingSize(int servingSize) {
        mServingSize = servingSize;
    }
}
