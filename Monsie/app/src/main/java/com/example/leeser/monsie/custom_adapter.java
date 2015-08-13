package com.example.leeser.monsie;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lisa Lee on 8/13/15.
 */

public class custom_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> imgid;


    public custom_adapter(Activity context, ArrayList<String> itemname, ArrayList<String> imgid) {
        super(context, R.layout.list_row, itemname);

        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_row, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.input_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_image);
        TextView date_view = (TextView) rowView.findViewById(R.id.date);

        DateFormat format = new SimpleDateFormat("MMM d");
        Date d = new Date();
        String date = format.format(d);
        date_view.setText(date);

        txtTitle.setText(itemname.get(position));
        // Change font
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "BEBAS.TTF");
        txtTitle.setTypeface(tf);
        Integer src = -1;
        if (imgid.get(position).equals("Happy")) {
            src = R.mipmap.happy2;
        } else if (imgid.get(position).equals("Sad")) {
            src = R.mipmap.sad2;
        }
        imageView.setImageResource(src);
        return rowView;
    };

}