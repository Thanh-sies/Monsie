package com.example.leeser.monsie;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    private String dateString = "";
    private static String inputString = "";

    int happy_count = 0;

    public int get_happy() {
        return happy_count;
    }
    public static int sad_count = 0;

    int get_sad() {
        return sad_count;
    }
    int total_count = 0;

    public int get_total() {
        return total_count;
    }

    protected final static String STORETEXT = "daily_logs.txt";
    private EditText textEditor;

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
                Intent goToDay = new Intent(daily_screen.this, daily_screen.class);
                startActivity(goToDay);
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
                } else if (done_button.isSelected() && image2.isSelected()) {
                    sad_count += 1;
                    total_count += 1;
                }

                Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);
                textEditor = (EditText) findViewById(R.id.editText);
                String text_entered = textEditor.getText().toString();
                goToMonth.putExtra("text1", text_entered);


                SharedPreferences var = getSharedPreferences("variables", MODE_PRIVATE);
                SharedPreferences.Editor edit = var.edit();
                edit.putInt("happies", happy_count);
                edit.putInt("sads", sad_count);
                edit.putInt("total", total_count);
                edit.commit();

                startActivity(goToMonth);

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