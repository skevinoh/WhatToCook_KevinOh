package com.example.skevinoh.whattocook_kevinoh;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by skevinoh on 3/21/18.
 */

public class Recipe {
    public String title;
    public String image;
    public String prepTime;
    public String instructionURL;
    public String description;
    public String label;
    public int servings;

    //connect variables with JSON
    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> recipelists = new ArrayList<Recipe>();
        try{
            String jsonString = loadJsonFromAsset("recipes.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");
            for (int i = 0; i < recipes.length(); i++){
                Recipe recipe = new Recipe();
                recipe.prepTime = recipes.getJSONObject(i).getString("prepTime");
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.image = recipes.getJSONObject(i).getString("image");
                recipe.instructionURL = recipes.getJSONObject(i).getString("url");
                recipe.label = recipes.getJSONObject(i).getString("dietLabel");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");
                recipelists.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipelists;
    }

    // JSON
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
