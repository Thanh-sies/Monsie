package com.example.leeser.monsie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lisa Lee on 8/13/15.
 */

public class custom_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<Boolean> imgid;

    public custom_adapter(Activity context, ArrayList<String> itemname, ArrayList<Boolean> imgid) {
        super(context, R.layout.monthly_row, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.monthly_row, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname.get(position));
        if (imgid.get(position)) {
            imageView.setImageResource(R.mipmap.happy2);
        } else {
            imageView.setImageResource(R.mipmap.sad2);
        }
        return rowView;
    };
}