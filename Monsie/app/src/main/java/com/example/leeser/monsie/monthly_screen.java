package com.example.leeser.monsie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.graphics.Typeface;

/**
 * Created by Lisa Lee on 7/24/15.
 */
public class monthly_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_screen);

        // get the text entered by user in daily_screen to show up on monthly screen
        TextView entered_text = (TextView) findViewById(R.id.textView);
        Intent i = getIntent();
        Bundle words = i.getExtras();
        if (words != null) {
            String text = (String) words.get("text1");
            entered_text.setText(text);
        }

        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button month_button = (Button) findViewById(R.id.monthview);
        final Button year_button = (Button) findViewById(R.id.yearview);

        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToDay = new Intent(monthly_screen.this, daily_screen.class);
                startActivity(goToDay);
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(monthly_screen.this, monthly_screen.class);
                startActivity(goToMonth);
            }
        });

        year_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(monthly_screen.this, year_screen.class);
                startActivity(goToYear);
            }
        });

    }

}