package com.example.skevinoh.whattocook_kevinoh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //on click, to search page
    public void onClickStart(View view){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }
}
