package com.example.leeser.monsie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import android.widget.Button;

import android.graphics.Typeface;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import android.content.Context;


/**
 * Created by Lisa Lee on 7/17/15.
 */
public class daily_screen extends Activity {

    public static HashMap<String, String> memomap = new HashMap<String, String>();
    private String dateString = "";
    private static String inputString = "";

    int happy_count = 0;

    public static int sad_count = 0;

    int total_count = 0;

    int happy_size = 1;
    int sad_size = 1;

//    Intent variables = new Intent(daily_screen.this, year_screen.class);
//    variables.putE


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_screen);

        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        dateString = printDate();
        dateView.setText(dateString);
        dateView.setTextSize(70);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
        dateView.setTypeface(tf);

        // emoji buttons
        final Button image1 = (Button) findViewById(R.id.selector1);
        final Button image2 = (Button) findViewById(R.id.selector2);
        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button month_button = (Button) findViewById(R.id.monthview);
        final Button year_button = (Button) findViewById(R.id.yearview);

        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(daily_screen.this, daily_screen.class);
                startActivity(goToMonth);
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(daily_screen.this, monthly_screen.class);
                startActivity(goToYear);
            }
        });

        year_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(daily_screen.this, year_screen.class);
                startActivity(goToYear);
            }
        });


        // REMEMBER TO CREDIT THE VECTOR IMAGE SOURCE:
        // http://www.vecteezy.com/vector-art/82360-rounded-emoticon-vectors-with-stroke
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundResource(R.mipmap.happy2);
                image2.setBackgroundResource(R.mipmap.sadbw);
                image1.setSelected(true);
                image2.setSelected(false);
                //                ViewGroup.LayoutParams size = image1.getLayoutParams();
//                size.width = 300;
//                size.height = 300;
//                image1.setLayoutParams(size);


            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setBackgroundResource(R.mipmap.sad2);
                image1.setBackgroundResource(R.mipmap.happybw);
                image1.setSelected(false);
                image2.setSelected(true);
            }
        });

        final Button done_button = (Button) findViewById(R.id.done_button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText userInput = (EditText) findViewById(R.id.editText);
//                inputString = userInput.getText().toString();
                done_button.setSelected(true);
                if (done_button.isSelected() && image1.isSelected()) {
                    happy_count += 1;
                    total_count += 1;
                    happy_size += 1;
                    sad_size -= 1;
                } else if (done_button.isSelected() && image2.isSelected()) {
                    sad_count += 1;
                    total_count += 1;
                    sad_size += 1;
                    happy_size -= 1;
                }

                Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);
                EditText text1 = (EditText) findViewById(R.id.editText);
                String text_entered = text1.getText().toString();
//                goToMonth.putExtra("text1", text_entered);


                SharedPreferences var = getSharedPreferences("variables", MODE_PRIVATE);
                SharedPreferences.Editor edit = var.edit();
                edit.putInt("happies", happy_count);
                edit.putInt("sads", sad_count);
                edit.putInt("total", total_count);
                edit.putString("input_text", text_entered);
                edit.putInt("happy_size", happy_size);
                edit.putInt("sad_size", sad_size);

                edit.commit();

                if (done_button.isSelected() && (image1.isSelected() || image2.isSelected())) {
                    startActivity(goToMonth);
                }

            }
        });
    }

    private void showDate() {
        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        dateString = printDate();
        dateView.setText(dateString);
        dateView.setTextSize(70);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
        dateView.setTypeface(tf);
    }

    private String printDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
