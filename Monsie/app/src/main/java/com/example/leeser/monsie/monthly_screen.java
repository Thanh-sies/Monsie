package com.example.leeser.monsie;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Lisa Lee on 7/24/15.
 */
public class monthly_screen extends Activity {
    String text;
    private ArrayList<String> text_arr = new ArrayList<>();
    private ArrayList<String> img_arr = new ArrayList<>();
    private custom_adapter listAdapter;
    private ListView lItems;
    boolean happy_select;
    boolean sad_select;
    boolean done_select;
    String date_check;

    protected static Bundle monthlyBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlyBundle = savedInstanceState;
        setContentView(R.layout.monthly_screen);

        SharedPreferences variables = getSharedPreferences("variables", 0);
        text = variables.getString("input_text", text);
        happy_select = variables.getBoolean("happy_select", happy_select);
        sad_select = variables.getBoolean("sad_select", sad_select);
        done_select = variables.getBoolean("done_select", done_select);
        date_check = variables.getString("date_check", date_check);

        readItems();

        lItems = (ListView) findViewById(R.id.listfeed);
        listAdapter = new custom_adapter(this, text_arr, img_arr);
        lItems.setAdapter(listAdapter);
        boolean isHappy = happy_select;
        date_check = printDate();
        if (done_select) {
            createNewEntry(isHappy, text);
            done_select = false;
            SharedPreferences.Editor edit = variables.edit();
            edit.putBoolean("done_select", done_select);
            edit.putString("date_check", date_check);
            edit.apply();
        }

        lItems.post(new Runnable() {
            @Override
            public void run() {
                lItems.smoothScrollToPosition(listAdapter.getCount() - 1);
            }
        });

        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button year_button = (Button) findViewById(R.id.yearview);

        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToDay = new Intent(monthly_screen.this, daily_screen.class);
                if (daily_screen.dailyBundle==null) {
                    startActivity(goToDay);
                } else{
                    onRestoreInstanceState(daily_screen.dailyBundle);
                }
            }
        });

        year_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(monthly_screen.this, year_screen.class);
                if (year_screen.yearBundle == null) {
                    startActivity(goToYear);
                } else {
                    onRestoreInstanceState(year_screen.yearBundle);
                }
            }
        });
    }

    private void createNewEntry(boolean isHappy, String txt) {
        text_arr.add(txt);
        if (isHappy) {
            img_arr.add("Happy");
        } else {
            img_arr.add("Sad");
        }
        listAdapter.notifyDataSetChanged();

        lItems.post(new Runnable() {
            @Override
            public void run() {
                lItems.smoothScrollToPosition(listAdapter.getCount() - 1);
            }
        });
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File msgFile = new File(filesDir, "msg.txt");
        File imgFile = new File(filesDir, "img.txt");
        try {
            text_arr = new ArrayList<String>(FileUtils.readLines(msgFile));
            img_arr = new ArrayList<String>(FileUtils.readLines(imgFile));
        } catch (IOException e) {
            text_arr = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File msgFile = new File(filesDir, "msg.txt");
        File imgFile = new File(filesDir, "img.txt");
        try {
            FileUtils.writeLines(msgFile, text_arr);
            FileUtils.writeLines(imgFile, img_arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String printDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        Date date = new Date();
        return dateFormat.format(date);
    }
}