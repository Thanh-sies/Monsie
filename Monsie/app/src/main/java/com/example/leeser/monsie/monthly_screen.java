package com.example.leeser.monsie;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

    boolean clicked = false;
    PopupWindow pop;
    LinearLayout layout;
    TextView tv;
    LinearLayout.LayoutParams params;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_screen);

        // get the text entered by user in daily_screen to show up on monthly screen
//        TextView entered_text = (TextView) findViewById(R.id.textView);
//        Intent i = getIntent();
//        Bundle words = i.getExtras();
//        if (words != null) {
//            String text = (String) words.get("text1");
//            entered_text.setText(text);
//        }

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

        pop = new PopupWindow(this);
        tv = new TextView(this);
        TextView showpopup = (TextView) findViewById(R.id.click);
        showpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    pop.showAtLocation(findViewById(R.id.monthly), Gravity.BOTTOM, 10, 10);
                    pop.update(50, 50, 300, 80);
                    clicked = false;
                } else {
                    pop.dismiss();
                    clicked = true;
                }
            }
        });

        tv.setText("Hi this is a sample text for popup window");
        pop.setContentView(findViewById(R.id.popup_layout));
        //pop.showAtLocation(layout, Gravity.CENTER, 100, 100);
//        mainLayout.addView(layout, params);
//        setContentView(R.layout.monthly_screen);
    }

}