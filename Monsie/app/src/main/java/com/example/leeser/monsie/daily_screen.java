package com.example.leeser.monsie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import android.widget.Button;

import android.graphics.Typeface;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import android.content.Context;


/**
 * Created by Lisa Lee on 7/17/15.
 */
public class daily_screen extends Activity {

    private String dateString = "";
    private static String inputString = "";
    public SharedPreferences variables;

//get variables from sharedpreferences
//     variables = PreferenceManager.getDefaultSharedPreferences(this);
////     = variables.getInt("happies", 1);
//    int sadSharedPreferences variables = getSharedPreferences("variables", 0);



    int happy_count;
    int sad_count;
    int total_count;
    int happy_size = 1;
    int sad_size = 1;
    protected final static String STORETEXT = "daily_logs.txt";
    private EditText textEditor;

//    Intent variables = new Intent(daily_screen.this, year_screen.class);
//    variables.putE


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_screen);

        variables = getSharedPreferences("variables", MODE_PRIVATE);
        happy_count = variables.getInt("happies", 1);
        sad_count = variables.getInt("sads", 1);
        total_count = variables.getInt("total", 1);
        happy_size =  variables.getInt("happy_size", 1);
        sad_size =  variables.getInt("sad_size", 1);

        // Show the date
        TextView dateView = (TextView) findViewById(R.id.date);
        dateString = printDate();
        dateView.setText(dateString);
        dateView.setTextSize(70);
        // Change font
        Typeface tf = Typeface.createFromAsset(getAssets(), "BEBAS.TTF");
        dateView.setTypeface(tf);


        // emoji buttons
        final Button image1 = (Button) findViewById(R.id.selector1);
        final Button image2 = (Button) findViewById(R.id.selector2);
        // navigation buttons
        final Button day_button = (Button) findViewById(R.id.dayview);
        final Button month_button = (Button) findViewById(R.id.monthview);
        final Button year_button = (Button) findViewById(R.id.yearview);


        day_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToMonth = new Intent(daily_screen.this, daily_screen.class);
                startActivity(goToMonth);
            }
        });

        month_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(daily_screen.this, monthly_screen.class);
                startActivity(goToYear);
            }
        });

        year_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToYear = new Intent(daily_screen.this, year_screen.class);
                startActivity(goToYear);
            }
        });


        // REMEMBER TO CREDIT THE VECTOR IMAGE SOURCE:
        // http://www.vecteezy.com/vector-art/82360-rounded-emoticon-vectors-with-stroke
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
                    happy_count += 1;
                    total_count += 1;
                    happy_size += 1;
                    sad_size -= 1;
                } else if (done_button.isSelected() && image2.isSelected()) {
                    sad_count += 1;
                    total_count += 1;
                    sad_size += 1;
                    happy_size -= 1;
                }

                Intent goToMonth = new Intent(daily_screen.this, monthly_screen.class);

                textEditor = (EditText) findViewById(R.id.editText);
                saveClicked(v);
                readFileInEditor();
                String text_entered = textEditor.getText().toString();
                goToMonth.putExtra("text1", text_entered);


                SharedPreferences.Editor edit = variables.edit();
                edit.putInt("happies", happy_count);
                edit.putInt("sads", sad_count);
                edit.putInt("total", total_count);
                edit.putString("input_text", text_entered);
                edit.putInt("happy_size", happy_size);
                edit.putInt("sad_size", sad_size);

                edit.apply();

                if (done_button.isSelected() && (image1.isSelected() || image2.isSelected())) {
                    startActivity(goToMonth);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please select an emoji first!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            Log.d("Activity", "Touch event " + event.getRawX() + ","
                    + event.getRawY() + " " + x + "," + y + " rect "
                    + w.getLeft() + "," + w.getTop() + "," + w.getRight()
                    + "," + w.getBottom() + " coords " + scrcoords[0] + ","
                    + scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {

                InputMethodManager imm =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
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

    public void saveClicked(View v) {

        try {

            OutputStreamWriter out=

                    new OutputStreamWriter(openFileOutput(STORETEXT, 0));

            out.write(textEditor.getText().toString());

            out.close();

        }

        catch (Throwable t) {

            Toast

                    .makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG)

                    .show();

        }

    }

    public void readFileInEditor()
    {

        try {

            InputStream in = openFileInput(STORETEXT);

            if (in != null) {

                InputStreamReader tmp=new InputStreamReader(in);

                BufferedReader reader=new BufferedReader(tmp);

                String str;

                StringBuilder buf=new StringBuilder();

                while ((str = reader.readLine()) != null) {

                    buf.append(str);

                }

                in.close();

                textEditor.setText(buf.toString());

            }

        }

        catch (java.io.FileNotFoundException e) {

        // that's OK, we probably haven't created it yet

        }

        catch (Throwable t) {

            Toast

                    .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)

                    .show();

        }

    }
}
