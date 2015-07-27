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

/**
 * Created by leeser on 7/17/15.
 */
public class daily_screen extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.daily_screen);

        final Button done_button = (Button) findViewById(R.id.button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);
                daily_screen.this.startActivity(goToMonth);
            }
        });
    }
}
