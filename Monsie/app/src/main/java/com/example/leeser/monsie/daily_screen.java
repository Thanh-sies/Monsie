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

/**
 * Created by leeser on 7/17/15.
 */
public class daily_screen extends ActionBarActivity {

    public static HashMap<String, String> memomap = new HashMap<String, String>();
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
                EditText text1 = (EditText) findViewById(R.id.editText);
                String text_entered = text1.getText().toString();
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
                showDate();
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
