package com.example.leeser.monsie;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import android.graphics.Typeface;
import android.widget.Button;
import android.content.SharedPreferences;

public class year_screen extends ActionBarActivity {
    int happy_count;
    int sad_count;
    int total_count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_screen);


        //get the emoji counts from daily_screen to change size of the emojis
        SharedPreferences variables = getSharedPreferences("variables", 0);
        happy_count = variables.getInt("happies", 1);
        sad_count = variables.getInt("sads", 1);
        total_count = variables.getInt("total", 1);

        //Change size of the emojis
        ImageView happy_view = (ImageView) findViewById(R.id.imageView);
        ViewGroup.LayoutParams happy_size = happy_view.getLayoutParams();
        happy_size.width = 100 * happy_count;
        happy_size.height = 100 * happy_count;
        happy_view.setLayoutParams(happy_size);

        ImageView sad_view = (ImageView) findViewById(R.id.imageView2);
        ViewGroup.LayoutParams sad_size = sad_view.getLayoutParams();
        sad_size.width = 100 * sad_count;
        sad_size.height = 100 * sad_count;
        sad_view.setLayoutParams(sad_size);



        // Show the date
        TextView yearTitle = (TextView) findViewById(R.id.yearlyReport);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_daily:
                setContentView(R.layout.daily_screen);
                return true;
            case R.id.action_monthly:
                setContentView(R.layout.monthly_screen);
                return true;
            case R.id.action_year:
                setContentView(R.layout.year_screen);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
