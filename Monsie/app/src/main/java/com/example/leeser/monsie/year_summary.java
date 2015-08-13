package com.example.leeser.monsie;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

/**
 * Created by xiuwenlu on 7/30/2015.
 */
public class year_summary extends Activity {
    int happy_count;
    int sad_count;
    int total_count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.year_summary);

        TextView yearSummary = (TextView) findViewById(R.id.total_summary);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(), "BEBAS.TTF");
        yearSummary.setTypeface(tf);

        //get the emoji counts from daily_screen and display them
        TextView happy = (TextView) findViewById(R.id.happy_summary);
        TextView sad = (TextView) findViewById(R.id.sad_summary);
        TextView total = (TextView) findViewById(R.id.total);

        SharedPreferences variables = getSharedPreferences("variables", 0);
        happy_count = variables.getInt("happies", 0);
        sad_count = variables.getInt("sads", 0);
        total_count = variables.getInt("total", 0);
        happy.setText("Happy: "+ happy_count);
        sad.setText("Sad: " + sad_count);
        total.setText("Total: " + total_count);

        final Button closeButton = (Button) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(year_summary.this, year_screen.class);
                startActivity(goToYear);
            }
        });

    }
}
