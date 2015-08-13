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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lisa Lee on 7/24/15.
 */
public class monthly_screen extends Activity {
    String text;
    private ListView lst;
    private custom_adapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_screen);

        // get the text entered by user in daily_screen to show up on monthly screen
        //TextView entered_text = (TextView) findViewById(R.id.textView);
        //TextView entered_text2 = (TextView) findViewById(R.id.textView2);

        SharedPreferences variables = getSharedPreferences("variables", 0);
        text = variables.getString("input_text", text);
        //entered_text.setText(text);

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Integer> img = new ArrayList<>();
        arr.add(text);
        img.add(R.mipmap.sad2);
        createNewEntry(arr, img);

//        TextView clickText = (TextView) findViewById(R.id.Itemname);
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
                Intent goToMonth = new Intent(monthly_screen.this, daily_screen.class);
                startActivity(goToMonth);
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(monthly_screen.this, monthly_screen.class);
                startActivity(goToYear);
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

    private void createNewEntry(ArrayList<String> arr, ArrayList<Integer> img) {
        lst = (ListView) findViewById(R.id.listfeed);
        listAdapter = new custom_adapter(this, arr, img);
        lst.setAdapter(listAdapter);
    }
}