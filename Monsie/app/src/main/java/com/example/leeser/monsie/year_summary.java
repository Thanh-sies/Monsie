package com.example.leeser.monsie;

import android.graphics.Typeface;
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
/**
 * Created by xiuwenlu on 7/30/2015.
 */
public class year_summary extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.year_summary);

        TextView yearSummary = (TextView) findViewById(R.id.total_summary);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(),"BEBAS.TTF");
        yearSummary.setTypeface(tf);


        final Button closeButton = (Button) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(year_summary.this, year_screen.class);
                startActivity(goToYear);
            }
        });

    }
}
