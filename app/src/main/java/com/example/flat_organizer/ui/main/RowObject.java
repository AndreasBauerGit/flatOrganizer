package com.example.flat_organizer.ui.main;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import java.util.Calendar;
import java.util.Date;

import com.example.flat_organizer.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import com.example.flat_organizer.R;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;


public class RowObject {
    Date currentTime = Calendar.getInstance().getTime();
    public String field1 ;
    public String field2 ;
    public String field3 ;
    public Context context;
    //private String checkbox ;


    public RowObject(String s1,String s2,String s3,Context c){
        field1=s1;
        field2=s2;
        field3=s3;
        context=c;
    };
    public void main(String[] args){
    };
    public void addRow(TableLayout tableLayout){
        TableRow tr = new TableRow(context);
        TextView t1 = new TextView(context);
        TextView t2 = new TextView(context);
        TextView t3 = new TextView(context);



        t1.setText(field1);
        t2.setText(field2);
        t3.setText(field3);

        t1.setTextSize(24);
        t2.setTextSize(24);
        t3.setTextSize(24);

        tr.addView(t1);
        tr.addView(t2);
        tr.addView(t3);
        checkbox(tr);
        tableLayout.addView(tr,0);
        Drawable background = tr.getBackground();
        int bkgc = Color.TRANSPARENT;
        if (background instanceof ColorDrawable) {
            bkgc = ((ColorDrawable) background).getColor();
        }
        Log.d("bkgc", "Color"+bkgc);
        View bl = new View(context);
        bl.setBackgroundColor(Color.parseColor("#070707"));
        bl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        tableLayout.addView(bl,0);
        //Log.d("color", "Value: " + date);


    }

    //private static final String TAG = "your activity name";
    public void checkbox(final TableRow tr){
        final CheckBox cb1 = new CheckBox(context);
        //cb1.setText("I've bought it.");
        cb1.setTextSize(24);
        tr.addView(cb1);
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (cb1.isChecked()) {
                    Log.d(field1,"sfdeswe");
                    Date currentTime = Calendar.getInstance().getTime();
                    String date =currentTime.toString();
                    String[] spliteddate = date.split("\\s+");
                    String s=spliteddate[2]+spliteddate[1]+spliteddate[5];
                    cb1.setText(s);
                    Log.d("ADebugTag", "Value: " + date);
                    //Log.d(TAG,currentTime.toString());
                    cb1.setTextSize(14);
                    tr.setBackgroundColor(Color.GRAY);
                }else{
                    cb1.setText("");
                    tr.setBackgroundColor(Color.TRANSPARENT);
                }



            }
        });
    }
}
