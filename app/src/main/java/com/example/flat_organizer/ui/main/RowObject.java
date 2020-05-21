package com.example.flat_organizer.ui.main;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import android.util.Log;

import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Map;

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


public class RowObject extends F1 implements View.OnClickListener {

    public String field1 ;
    public String field2 ;
    public String field3 ;
    public Context context;
    public CheckBox cb;
    public TextView t1;
    public TextView t2;
    public TextView t3;
    public TableRow tr; // "member variable"
    public View bl;
    public int tr_id;
    public int sep_id;
    public TableLayout tableLayout;
    //private Fragment parent_fragment;
    public List row_obj_list;
    //private String checkbox ;


    public RowObject(String s1,String s2,String s3,Context c, TableLayout t, List ro_list){
        //Log.d("input", s1.getClass().getName());

        field1 = s1;
        field2 = s2;
        field3 = s3;

        if (s1.isEmpty()){
            field1 = "      ";
        }
        if (s2.isEmpty()){
            field2 = "      ";
        }

        if (s3.isEmpty()){
            field3 = "      ";
        }

        context = c;
        tableLayout =  t;
        row_obj_list = ro_list;
        // parent_fragment = parent;
    }

    public void main(String[] args){
    }

    public void addRow(){

        row_obj_list.add(this);

        // imporve by maybe using an xml file template!!!!!!!!!!!!!!


        TableRow tr = new TableRow(context);
        tr.setId(View.generateViewId());
        t1 = addTextView(field1);
        t2 = addTextView(field2);
        t3 = addTextView(field3);
        tr_id = tr.getId();





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
        add_separating_line(0);
        tableLayout.addView(tr,0);

        // getting the current background color
        Drawable background = tr.getBackground();
        int bkgc = Color.TRANSPARENT;
        if (background instanceof ColorDrawable) {
            bkgc = ((ColorDrawable) background).getColor();
        }

        tr.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        // a separator line


        //Log.d("color", "Value: " + date);


    }

    public CheckBox addCheckBox(){
        CheckBox cb = new CheckBox(context);
        cb.setId(View.generateViewId());
        //cb1.setText("I've bought it.");
        cb.setTextSize(16);
        cb.setIncludeFontPadding(false);
        cb.setMinHeight(0);
        cb.setMinWidth(0);
        //cb.setBackgroundColor(0x00000000);
        cb.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cb.setOnClickListener(this);
        return cb;

    }

    @Override
    public void onClick(View v) {

        TableRow tr_local = tableLayout.findViewById(tr_id);
        View bl_local = tableLayout.findViewById(sep_id);


        if (cb.isChecked()) {
            Date currentTime = Calendar.getInstance().getTime();
            String date = currentTime.toString();
            String[] spliteddate = date.split("\\s+");
            String s = spliteddate[2] + spliteddate[1] + spliteddate[5];
            cb.setText(s);
            //Log.d(TAG,currentTime.toString());

            // find this by id....
            tr_local.setBackgroundColor(Color.GRAY);

        }else{
            cb.setText("");
            tr_local.setBackgroundColor(Color.TRANSPARENT);
        }

        arrange_rows();



    }

    public TextView addTextView(String text){
        TextView t = new TextView(context);
        t.setId(View.generateViewId());
        t.setText(text);
        t.setTextSize(24);
        return t;

    }

    void add_separating_line(int index){

        View bl = new View(context);
        bl.setBackgroundColor(Color.parseColor("#070707"));
        bl.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        bl.setId(View.generateViewId());
        sep_id = bl.getId();
        tableLayout.addView(bl,index);

    }

    void arrange_rows(){

        // probably google how other people do that// find actuall example from other apps
        // then again works prettie nicely

        // dictionary mapping all row positions to position in object list
        // tree map is automatically sorted
        Map<Integer, Integer> row_obj_id = new TreeMap<Integer, Integer>();


        List<TableRow> row_list = new ArrayList<TableRow>();
        List<TableRow> row_checked = new ArrayList<TableRow>();
        List<View> bl_list = new ArrayList<View>();
        List<View> bl_checked = new ArrayList<View>();


        // sorting row ids
        for (int i = 0; i < row_obj_list.size(); i++){
            RowObject l_obj = (RowObject) row_obj_list.get(i);
            TableRow tr_local = tableLayout.findViewById(l_obj.tr_id);
            int row_id = tableLayout.indexOfChild(tr_local);
            row_obj_id.put(row_id, i);
        }

        // adding rows and line objects to a list in the correct order
        for (int row_id : row_obj_id.keySet()) {
            int id = row_obj_id.get(row_id);
            RowObject l_obj = (RowObject) row_obj_list.get(id);
            TableRow tr_local = tableLayout.findViewById(l_obj.tr_id);
            View bl_local = tableLayout.findViewById(l_obj.sep_id);

            // sorting, in correct order into checked and unchecked
            if (!l_obj.cb.isChecked()) {
                row_list.add(tr_local);
                bl_list.add(bl_local);
            } else {
                row_checked.add(tr_local);
                bl_checked.add(bl_local);
            }
        }

        // appending checked list at the end
        row_list.addAll(row_checked);
        bl_list.addAll(bl_checked);

        // remove all existing rows
        for (int i = 0; i<row_list.size(); i++) {
            tableLayout.removeView(bl_list.get(i)); // remove row
            tableLayout.removeView(row_list.get(i)); // remove seperating line
        }

        // add all views in correct order
        for (int i = 0; i<row_list.size(); i++) {
            tableLayout.addView(row_list.get(i), i * 2);
            tableLayout.addView(bl_list.get(i), (i * 2) +1);
        }

    }
}
