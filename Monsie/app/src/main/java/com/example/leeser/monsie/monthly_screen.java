package com.example.leeser.monsie;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Lisa Lee on 7/24/15.
 */
public class monthly_screen extends Activity {
    String text;
    private ArrayList<String> text_arr = new ArrayList<>();
    private ArrayList<Boolean> img_arr = new ArrayList<>();
    private custom_adapter listAdapter;
    private ListView lItems;
    boolean happy_select;
    boolean sad_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_screen);

        // get the text entered by user in daily_screen to show up on monthly screen
        //TextView entered_text = (TextView) findViewById(R.id.textView);
        //TextView entered_text2 = (TextView) findViewById(R.id.textView2);

        SharedPreferences variables = getSharedPreferences("variables", 0);
        text = variables.getString("input_text", text);
        happy_select = variables.getBoolean("happy_select", happy_select);
        sad_select = variables.getBoolean("sad_select", sad_select);
        //entered_text.setText(text);

        readItems();

        lItems = (ListView) findViewById(R.id.listfeed);
        listAdapter = new custom_adapter(this, text_arr, img_arr);
        lItems.setAdapter(listAdapter);
        boolean isHappy = happy_select;
        createNewEntry(isHappy, text);

//        TextView clickText = (TextView) findViewById(R.id.Itemname);
//
//        final RelativeLayout popup = (RelativeLayout) findViewById(R.id.popup);
//        clickText.setOnClickListener(new View.OnClickListener() {
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

        year_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(monthly_screen.this, year_screen.class);
                startActivity(goToYear);
            }
        });
    }

    private void createNewEntry(boolean isHappy, String txt) {
        text_arr.add(txt);
        img_arr.add(isHappy);
        listAdapter.notifyDataSetChanged();
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File msgFile = new File(filesDir, "msg.txt");
        File imgFile = new File(filesDir, "img.txt");
        try {
            text_arr = new ArrayList<String>(FileUtils.readLines(msgFile));
            img_arr = new ArrayList<Boolean>(FileUtils.readLines(imgFile));
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
}