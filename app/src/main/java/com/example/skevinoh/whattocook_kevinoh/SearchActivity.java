package com.example.skevinoh.whattocook_kevinoh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by skevinoh on 3/21/18.
 */

public class SearchActivity extends AppCompatActivity {
    private Context mContext;
    private Spinner mPrepSpinner;
    private Spinner mDietSpinner;
    private Spinner mServingSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this;
        mDietSpinner = findViewById(R.id.diet_spinner);
        mPrepSpinner = findViewById(R.id.cooking_time_spinner);
        mServingSpinner = findViewById(R.id.serving_size_spinner);
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        ArrayList<String> array = new ArrayList<String>();
        array.add("");
        //dietSpinner
        for(int position = 0; position < recipeList.size(); position++){
            String curr = recipeList.get(position).label;
            if (!array.contains(curr)){
                array.add(curr);
            }

        }
        //dietSpinner dropdown
        ArrayAdapter<String> diet = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array);
        mDietSpinner.setAdapter(diet);
        //serving dropdown
        String[] serving = new String[]{"", "Less than 4", "4-6", "7-9", "More than 10"};
        ArrayAdapter<String> servingAdpat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serving);
        mServingSpinner.setAdapter(servingAdpat);
        //cooking time spinner
        String[] cooking_time = new String[]{"", "30 min or less", "Less than 1 hr", "More than 1 hr"};
        ArrayAdapter<String> timeAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cooking_time);
        mPrepSpinner.setAdapter(timeAdapt);


    }
    public void onClick(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), ResultActivity.class);
        nextScreen.putExtra("label", mDietSpinner.getSelectedItem().toString());
        nextScreen.putExtra("serving", mServingSpinner.getSelectedItem().toString());
        nextScreen.putExtra("time", mPrepSpinner.getSelectedItem().toString());

        startActivity(nextScreen);
    }
}