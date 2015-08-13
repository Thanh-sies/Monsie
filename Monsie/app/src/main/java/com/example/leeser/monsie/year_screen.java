package com.example.leeser.monsie;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.FrameLayout.LayoutParams;

public class year_screen extends Activity {
    int happy_count;
    int sad_count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_screen);


        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button month_button = (Button) findViewById(R.id.monthview);
        final Button year_button = (Button) findViewById(R.id.yearview);

        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToDay = new Intent(year_screen.this, daily_screen.class);
                startActivity(goToDay);
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(year_screen.this, monthly_screen.class);
                startActivity(goToMonth);
            }
        });

        year_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(year_screen.this, year_screen.class);
                startActivity(goToYear);
            }
        });

        //get the emoji counts from daily_screen to change size of the emojis
        SharedPreferences variables = getSharedPreferences("variables", 0);
        happy_count = variables.getInt("happy_size", 1);
        sad_count = variables.getInt("sad_size", 1);



        //Change size of the emojis
        ImageView happy_view = (ImageView) findViewById(R.id.imageView);
        ViewGroup.LayoutParams happy_size = happy_view.getLayoutParams();
        happy_size.width = 105 * happy_count;
        happy_size.height = 105 * happy_count;

        happy_view.setLayoutParams(happy_size);

        ImageView sad_view = (ImageView) findViewById(R.id.imageView2);
        ViewGroup.LayoutParams sad_size = sad_view.getLayoutParams();


        sad_size.width = 105 * sad_count;
        sad_size.height = 105 * sad_count;
        sad_view.setLayoutParams(sad_size);



        // Show the date
        TextView yearTitle = (TextView) findViewById(R.id.yearlyReport);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(), "BEBAS.TTF");
        yearTitle.setTypeface(tf);

        final Button summary_button = (Button) findViewById(R.id.summary_button);

        summary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSummary = new Intent(year_screen.this, year_summary.class);
                startActivity(goToSummary);
            }
        });
    }



}
