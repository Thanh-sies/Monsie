package com.example.leeser.monsie;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leeser on 7/17/15.
 */
public class daily_screen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.daily_screen);

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

        TextView dayView = (TextView) findViewById(R.id.date);
        dayView.setText(printDate());
        dayView.setTextSize(35);
    }

    private String printDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        Date date = new Date();
        return dateFormat.format(date);
    }


}

