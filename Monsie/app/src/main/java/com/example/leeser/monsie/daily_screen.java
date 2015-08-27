package com.example.leeser.monsie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.Button;

import android.graphics.Typeface;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

/**
 * Vector images in this application was taken from
 * http://www.vecteezy.com/vector-art/82360-rounded-emoticon-vectors-with-stroke
 * created by designer zhaolifang
 *
 * Created by Lisa Lee on 7/17/15.
 */
public class daily_screen extends Activity {

    private String dateString = "";
    private static String inputString = "";
    public SharedPreferences variables;

    int happy_count;
    int sad_count;
    int total_count;
    int happy_size;
    int sad_size;
    String date_check;
    boolean happy_select;
    boolean sad_select;
    boolean done_select;
    private EditText textEditor;

    protected static Bundle dailyBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyBundle = savedInstanceState;
        setContentView(R.layout.daily_screen);

        variables = getSharedPreferences("variables", MODE_PRIVATE);
        happy_count = variables.getInt("happies", 0);
        sad_count = variables.getInt("sads", 0);
        total_count = variables.getInt("total", 0);
        happy_size =  variables.getInt("happy_size", 0);
        sad_size =  variables.getInt("sad_size", 0);
        happy_select = variables.getBoolean("happy_select",false);
        sad_select = variables.getBoolean("sad_select", false);
        date_check = variables.getString("date_check", date_check);
        done_select = variables.getBoolean("done_select", done_select);


        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        dateString = printDate();
        dateView.setText(dateString);
        dateView.setTextSize(70);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(), "BEBAS.TTF");
        dateView.setTypeface(tf);

        //limit to max 140 chararacters.
        EditText inputs = (EditText) findViewById(R.id.editText);
        inputs.setFilters(new InputFilter[] {new InputFilter.LengthFilter(100)});



        // emoji buttons
        final Button image1 = (Button) findViewById(R.id.selector1);
        final Button image2 = (Button) findViewById(R.id.selector2);
        // navigation buttons
        final Button month_button = (Button) findViewById(R.id.monthview);
        final Button year_button = (Button) findViewById(R.id.yearview);

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);
                if (monthly_screen.monthlyBundle == null) {
                    startActivity(goToMonth);
                    finish();
                } else {
                    onRestoreInstanceState(monthly_screen.monthlyBundle);
                }
            }
        });

        year_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(daily_screen.this, year_screen.class);
                if (year_screen.yearBundle == null) {
                    startActivity(goToYear);
                    finish();
                } else {
                    onRestoreInstanceState(year_screen.yearBundle);
                }

            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundResource(R.mipmap.happy2);
                image2.setBackgroundResource(R.mipmap.sadbw);
                image1.setSelected(true);
                image2.setSelected(false);

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
                done_button.setSelected(true);

                if (done_button.isSelected() && image1.isSelected()) {
                    sad_select = false;
                    happy_select = true;
                    happy_count += 1;
                    total_count += 1;
                    happy_size += 1;
                    if (happy_size >= 3) {
                        happy_size = 3;
                    }
                    sad_size -= 1;
                    if (sad_size < 0) {
                        sad_size = 1;
                    };

                } else if (done_button.isSelected() && image2.isSelected()) {
                    happy_select = false;
                    sad_select = true;
                    sad_count += 1;
                    total_count += 1;
                    sad_size += 1;
                    if (sad_size  >= 3) {
                        sad_size = 3;
                    }
                    happy_size -= 1;
                    if (happy_size < 0) {
                        happy_size = 1;
                    }
                } else {
                    done_button.setSelected(false);
                }

                done_select = done_button.isSelected();
                SharedPreferences.Editor edit = variables.edit();

                if (done_button.isSelected() && (image1.isSelected() || image2.isSelected()) &&
                        !dateString.equals(date_check)) {

                    Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);

                    textEditor = (EditText) findViewById(R.id.editText);
                    String text_entered = textEditor.getText().toString();
                    goToMonth.putExtra("text1", text_entered);


                    edit.putInt("happies", happy_count);
                    edit.putInt("sads", sad_count);
                    edit.putInt("total", total_count);
                    edit.putString("input_text", text_entered);
                    edit.putInt("happy_size", happy_size);
                    edit.putInt("sad_size", sad_size);
                    edit.putBoolean("happy_select", happy_select);
                    edit.putBoolean("sad_select", sad_select);
                    edit.putString("date_check", date_check);
                    edit.putBoolean("done_select", done_select);

                    edit.apply();
                    startActivity(goToMonth);
                    finish();
                } else {
                    CharSequence text;
                    done_select = false;
                    edit.putBoolean("done_select", done_select);
                    edit.apply();
                    if (!image1.isSelected() && !image2.isSelected()) {
                         text = "Please select an emoji first!";
                    } else {
                         text = "You already made an entry for today!";
                    }
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
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

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        super.onResume();
    }

    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
}

