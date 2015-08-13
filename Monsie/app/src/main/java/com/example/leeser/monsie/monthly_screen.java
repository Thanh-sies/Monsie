package com.example.leeser.monsie;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Lisa Lee on 7/24/15.
 */
public class monthly_screen extends Activity {
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_screen);

        // get the text entered by user in daily_screen to show up on monthly screen
        TextView entered_text = (TextView) findViewById(R.id.textView);
//        TextView entered_text2 = (TextView) findViewById(R.id.textView2);
//        Intent i = getIntent();
//        Bundle words = i.getExtras();
//        if (words != null) {
//            String text = (String) words.get("text1");
//            entered_text.setText(text);
//        }

        SharedPreferences variables = getSharedPreferences("variables", 0);
        text = variables.getString("input_text", text);
        entered_text.setText(text);

//        TextView clickText = (TextView) findViewById(R.id.clickme);
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

//        final Button stupid = (Button) findViewById(R.id.done_button);
//        stupid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent goToYear = new Intent(monthly_screen.this, year_screen.class);
//                monthly_screen.this.startActivity(goToYear);
//
//            }
//        });
    }

}