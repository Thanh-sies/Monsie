package com.example.leeser.monsie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.graphics.Typeface;
import android.widget.Toast;

/**
 * Created by leeser on 7/17/15.
 */
public class daily_screen extends ActionBarActivity {

    public static HashMap<String, String> memomap = new HashMap<String, String>();
    private String date = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_screen);

        // REMEMBER TO CREDIT THE VECTOR IMAGE SOURCE:
        // http://www.vecteezy.com/vector-art/82360-rounded-emoticon-vectors-with-stroke
        final ImageButton image1 = (ImageButton) findViewById(R.id.imageButton);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToCal = new Intent(daily_screen.this, monthly_screen.class);
                daily_screen.this.startActivity(imageToCal);
            }
        });
        final ImageButton image2 = (ImageButton) findViewById(R.id.imageButton2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image2ToCal = new Intent(daily_screen.this, monthly_screen.class);
                daily_screen.this.startActivity(image2ToCal);
            }
        });

        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        date = printDay() + printMonth() + printDate();
        dateView.setText(date);
        dateView.setTextSize(45);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
        dateView.setTypeface(tf);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        date = printDay() + printMonth() + printDate();
        dateView.setText(date);
        dateView.setTextSize(45);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
        dateView.setTypeface(tf);
    }

    private String printDay() {
        DateFormat dateFormat = new SimpleDateFormat("EEE");
        Date date = new Date();
        return dateFormat.format(date) + "\n";
    }

    private String printMonth() {
        DateFormat dateFormat = new SimpleDateFormat("MMM");
        Date date = new Date();
        return dateFormat.format(date) + "\n";
    }

    private String printDate() {
        DateFormat dateFormat = new SimpleDateFormat("d");
        Date date = new Date();
        return dateFormat.format(date) + "\n";
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
                onResume();
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
