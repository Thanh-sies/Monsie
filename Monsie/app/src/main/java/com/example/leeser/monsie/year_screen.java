package com.example.leeser.monsie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Button;
import android.content.SharedPreferences;


public class year_screen extends Activity {
    int happy_count;
    int sad_count;
    int total_count;
    protected static Bundle yearBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yearBundle = savedInstanceState;
        setContentView(R.layout.year_screen);


        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button month_button = (Button) findViewById(R.id.monthview);

        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToDay = new Intent(year_screen.this, daily_screen.class);
                if (daily_screen.dailyBundle==null) {
                    startActivity(goToDay);
                } else{
                    onRestoreInstanceState(daily_screen.dailyBundle);
                }
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(year_screen.this, monthly_screen.class);
                if (monthly_screen.monthlyBundle == null){
                    startActivity(goToMonth);
                } else {
                    onRestoreInstanceState(monthly_screen.monthlyBundle);
                }
            }
        });


        //get the emoji counts from daily_screen to change size of the emojis
        SharedPreferences variables = getSharedPreferences("variables", 0);

        happy_count = variables.getInt("happy_size", happy_count);
        sad_count = variables.getInt("sad_size", happy_count);
        total_count = variables.getInt("total_count", total_count);



        //Change size of the emojis
        ImageView happy_view = (ImageView) findViewById(R.id.imageView);
        ViewGroup.LayoutParams happy_size = happy_view.getLayoutParams();
        happy_size.width = 105 * happy_count;
        happy_size.height = 105 * happy_count;
        happy_view.setLayoutParams(happy_size);
        System.out.println(happy_count + " " + sad_count);

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


//
//        final RelativeLayout popup = (RelativeLayout) findViewById(R.id.popup_summary);
//        summary_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (popup.getVisibility() == View.INVISIBLE) {
//                    popup.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        Button removePopup = (Button) findViewById(R.id.ok_button);
//        removePopup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popup.setVisibility(View.INVISIBLE);
//            }
//        });
    }
}
