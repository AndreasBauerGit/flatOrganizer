package com.example.flat_organizer.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
    public String field1 ;
    public String field2 ;
    public String field3 ;
    public Context context;
    public CheckBox cb;
    public TextView t1;
    public TextView t2;
    public TextView t3;
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

        // imporve by maybe using an xml file template!!!!!!!!!!!!!!


        TableRow tr = new TableRow(context);
        tr.setId(View.generateViewId());
        t1 = addTextView(field1);
        t2 = addTextView(field2);
        t3 = addTextView(field3);



        // constraint layout object and a guidline
        ConstraintLayout cl = new ConstraintLayout(context);
        cl.setId(View.generateViewId());

        EditText et4 = new EditText(context); // textview below check box
        et4.setId(View.generateViewId());
        et4.setText("test");
        et4.setTextSize(16);
        et4.setIncludeFontPadding(false);
        et4.setMinHeight(0);
        et4.setMinWidth(0);
        //et4.setMaxHeight(0);





        // constructing text box
        cb = addCheckBox();

        // adding checkbox and one textfield
        cl.addView(et4);
        cl.addView(cb);


        //cl.addView(et4, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT));
        // producing constraints


        ConstraintSet constraintSet = new ConstraintSet();
        //constraintSet.clone(cl);
        //constraintSet.constrainWidth(et4.getId(),LayoutParams.WRAP_CONTENT);
        constraintSet.constrainHeight(et4.getId(),LayoutParams.WRAP_CONTENT);
       // constraintSet.constrainWidth(cb.getId(),LayoutParams.WRAP_CONTENT);
        constraintSet.constrainHeight(cb.getId(),LayoutParams.WRAP_CONTENT);
        constraintSet.connect(et4.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(et4.getId(), ConstraintSet.TOP, cb.getId(), ConstraintSet.BOTTOM);
        //constraintSet.connect(et4.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 80);
        //constraintSet.connect(et4.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        // adding margines for both check box and text field at the rigth hand side


        constraintSet.applyTo(cl);
        et4.setTranslationY(-20);
        // setting width and height
        //cl.setLayoutParams(new LayoutParams(110, LayoutParams.WRAP_CONTENT));

        tr.addView(t1);
        tr.addView(t2);
        tr.addView(t3);
        tr.addView(cl);



        tableLayout.addView(tr,0);
        tr.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        // a separator line
        View bl = new View(context);
        bl.setBackgroundColor(Color.parseColor("#070707"));
        bl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        tableLayout.addView(bl,0);


    }

    public CheckBox addCheckBox(){
        CheckBox cb = new CheckBox(context);
        cb.setId(View.generateViewId());
        //cb1.setText("I've bought it.");
        cb.setTextSize(16);
        cb.setIncludeFontPadding(false);
        cb.setText("test2");
        cb.setMinHeight(0);
        cb.setMinWidth(0);
        //cb.setBackgroundColor(0x00000000);
        cb.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(field1,"sfdeswe");
            }
        });
        return cb;

    }

    public TextView addTextView(String text){
        TextView t = new TextView(context);
        t.setId(View.generateViewId());
        t.setText(text);
        t.setTextSize(24);
        return t;

    }
}
