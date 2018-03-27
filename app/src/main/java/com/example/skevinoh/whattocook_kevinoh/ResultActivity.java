package com.example.skevinoh.whattocook_kevinoh;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by skevinoh on 3/21/18.
 */

public class ResultActivity extends AppCompatActivity {
    private Context mContext;
    private TextView mTextView;
    private ListView mListView;
    private ImageButton imageButton;

    private ArrayList<Recipe> searchList(ArrayList<Recipe> list) {
        ArrayList<Recipe> searchFunctionList = new ArrayList<>();
        int bigTime;
        int smallTime;
        int bigServing;
        int lowServing;
        String labelTag = this.getIntent().getExtras().getString("label");


        //cases for prep Time
        String prepTime = this.getIntent().getExtras().getString("time");
        if (prepTime.equals("30 min or less")) {
            bigTime = 30;
            smallTime = 0;
        } else if (prepTime.equals("Less than 1 hr")) {
            bigTime = 59;
            smallTime = 0;
        } else if (prepTime.equals("More than 1 hr")) {
            bigTime = 480;
            smallTime = 60;
        } else {
            bigTime = 600;
            smallTime = 0;
        }
        //cases for servings
        String servings = this.getIntent().getExtras().getString("serving");
        if (servings.equals("Less than 4")) {
            bigServing = 3;
            lowServing = 1;
        } else if (servings.equals("4-6")) {
            bigServing = 6;
            lowServing = 4;
        } else if (servings.equals("7-9")) {
            bigServing = 9;
            lowServing = 7;
        } else if (servings.equals("Pick One")) {
            bigServing = 75;
            lowServing = 1;
        } else {
            bigServing = 100;
            lowServing = 20;
        }
        //add to ArrayList
        ArrayList<Integer> times = timeChange(list);
        for (int i = 0; i < list.size(); i++) {
            if (!labelTag.equals("Pick One")) {
                if ((list.get(i).label.equals(labelTag)) && (lowServing <= list.get(i).servings) && (bigServing >= list.get(i).servings)
                        && (times.get(i) >= smallTime) && (times.get(i) <= bigTime)) {
                    searchFunctionList.add(list.get(i));

                } else {
                    if ((bigServing >= list.get(i).servings) && (lowServing <= list.get(i).servings) && (times.get(i) >= smallTime) && (times.get(i) <= bigTime)) {
                        searchFunctionList.add(list.get(i));
                    }
                }
            }

        }return searchFunctionList;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        ArrayList<Recipe> recipeArrayList = Recipe.getRecipesFromFile("recipes.json", this);
        ArrayList<Recipe> searchList = searchList(recipeArrayList);
        mContext = this;
        RecipeAdapter adapter = new RecipeAdapter(this, searchList);
        mTextView = findViewById(R.id.text_view);
        mListView = findViewById(R.id.list_view);
        imageButton = findViewById(R.id.start_cooking_icon);
        mTextView.setText( "Found " + searchList.size() + " recipe(s)");
        mListView.setAdapter(adapter);


    }
    private ArrayList<Integer> timeChange(ArrayList<Recipe> list){
        ArrayList<Integer> times = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            int min = 0;
            String pTime = list.get(i).prepTime;
            String[] itemsHolder = pTime.split(" ");
            ArrayList<String> items = new ArrayList<>(Arrays.asList(itemsHolder));
            if( items.contains("hour") || items.contains("hours")){
                int k = items.indexOf("hours");
                if(k != -1){
                    min += 60*(Integer.valueOf(items.get(k-1)));
                } else {
                    min += 60;
                }
            }
            if(items.contains("minutes")){
                int k = items.indexOf("minutes");
                min +=  Integer.valueOf(items.get(k-1));
            }else{
                min += 60;
            } times.add(min);
        } return times;
    }

}
