package com.example.flatOrganizer.ui.main;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.IntStream;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.widget.TextView;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableRow;
import android.widget.CheckBox;


public class RowLayoutManager extends TableFragment implements View.OnClickListener {

    public String field1 ;
    public String field2 ;
    public String field3 ;
    public int list_pos;
    public Context context;
    public CheckBox cb;
    public TextView t1;
    public TextView t2;
    public TextView t3;
    public TableRow tabelrow;
    public View line;
    public int view_id;
    public int sep_id;
    public TableLayout tableLayout;



    public RowLayoutManager(TableLayout t, int list_pos_, Context context_){
        //Log.d("input", s1.getClass().getName());
        list_pos = list_pos_;
        field1 = RowData.field1.get(list_pos);
        field2 = RowData.field2.get(list_pos);
        field3 = RowData.field3.get(list_pos);
        context = context_; // TODO is this how you should set fields in the constructor??
        tableLayout =  t;



        tabelrow = new TableRow(context);
        // makeing sure that the view object i is the same as listed in RowData.unique_ids
        view_id = RowData.id_unique.get(list_pos);
        tabelrow.setId(view_id); //TODO: might override other view ids
        // border outline for debugging
        //tr.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border));
        //

        t1 = addTextView(field1);
        t2 = addTextView(field2);
        t3 = addTextView(field3);



        ConstraintLayout cl = new ConstraintLayout(context);
        cl.setId(View.generateViewId());

        // constructing text box
        cb = addCheckBox();
        // adding checkbox and one textfield
        ConstraintSet constraintSetCB = new ConstraintSet();
        constraintSetCB.applyTo(cl);

        cl.addView(cb);
        ConstraintSet constraintSetCl = new ConstraintSet();
        constraintSetCl.constrainHeight(cl.getId(),LayoutParams.WRAP_CONTENT);
        constraintSetCl.applyTo(cl);



        // setting width and height
        //cl.setLayoutParams(new LayoutParams(110, LayoutParams.WRAP_CONTENT));

        tabelrow.addView(t1);
        tabelrow.addView(t2);
        tabelrow.addView(t3);
        tabelrow.addView(cl);
        add_separating_line(0);
        tableLayout.addView(tabelrow,0);

        // getting the current background color
        Drawable background = tabelrow.getBackground();
        int bkgc = Color.TRANSPARENT;
        if (background instanceof ColorDrawable) {
            bkgc = ((ColorDrawable) background).getColor();
        }

        tabelrow.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }

    public CheckBox addCheckBox(){
        CheckBox cb = new CheckBox(context);
        cb.setId(View.generateViewId());
        cb.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cb.setOnClickListener(this);

        return cb;

    }

    @Override
    public void onClick(View v) {

        // adding or manipulating to checked list in RowData
        RowData.checked.add(list_pos, cb.isChecked());

        if (cb.isChecked()) {
            Date currentTime = Calendar.getInstance().getTime();
            RowData.time.add(list_pos, currentTime);
            String date = currentTime.toString();
            String[] spliteddate = date.split("\\s+");
            String s = spliteddate[2] + spliteddate[1] + spliteddate[5];
            cb.setText(s);
            //Log.d(TAG,currentTime.toString());

            // find this by id....
            tabelrow.setBackgroundColor(Color.GRAY);

        }else{
            RowData.time.add(list_pos, null);
            cb.setText("");
            tabelrow.setBackgroundColor(Color.TRANSPARENT);
        }

        arrange_rows(list_pos, tableLayout);
    }

    public TextView addTextView(String text){
        TextView t = new TextView(context);
        t.setId(View.generateViewId());
        t.setText(text);
        t.setTextSize(24);
        return t;

    }

    private void add_separating_line(int index){

        line = new View(context);
        line.setBackgroundColor(Color.parseColor("#070707"));
        line.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        line.setId(View.generateViewId());
        sep_id = line.getId();
        tableLayout.addView(line,index);

    }

    private static void arrange_rows(int list_pos, TableLayout tableLayout){

        // probably google how other people do that// find actuall example from other apps
        // then again works pretty nicely

        // dictionary mapping all row positions to position in object list
        // tree map is automatically sorted

        // find highest uncheck position
        Log.d("list_before", RowData.layout_position.toString());
        int original_pos = RowData.layout_position.get(list_pos);
        Log.d("list_before", Integer.toString(original_pos));
        int highest_unchecked_pos = Collections.max(RowData.layout_position);
        Log.d("highest_unchecked_pos1", Integer.toString(highest_unchecked_pos));
        for (int i = 0; i < RowData.id_unique.size(); i++) {
            // sorting, in correct order into checked and unchecked
            if (!RowData.checked.get(i) && (RowData.layout_position.get(i) > highest_unchecked_pos)) {
                highest_unchecked_pos = RowData.layout_position.get(i);
            }
        }
        Log.d("highest_unchecked_pos2", Integer.toString(highest_unchecked_pos));
       // rearranging in list TODO: -1 should not be a problem right??
        for (int i = 0; i < RowData.id_unique.size(); i++) {
            int pos = RowData.layout_position.get(i);
            // move a row up for all values between the position of the originally checked row
            // and the highest unchecked row
            if   ((pos > original_pos) && (pos <= highest_unchecked_pos)){
                RowData.layout_position.set(i, pos - 1);
            // move down all unchecked rows below the newly inserted one
            } else if (pos > highest_unchecked_pos){
                RowData.layout_position.set(i, pos + 1);
            }
        }
        Log.d("list_after_1", RowData.layout_position.toString());
        // change position of new inserted row
        RowData.layout_position.set(list_pos, highest_unchecked_pos);


        // remove all existing rows
        for (int i = 0; i < RowData.id_unique.size(); i++) {
            tableLayout.removeView(RowData.row_obj_list.get(i).tabelrow); // remove row
            tableLayout.removeView(RowData.row_obj_list.get(i).line); // remove seperating line
        }

        // TODO implement sorting by data added for upper and lower lists
        // write a dictionary(layout_pos: list_pos)
        HashMap<Integer, Integer> pos_map = new HashMap<>();
        for (int i = 0; i < RowData.layout_position.size(); i++)
            pos_map.put(RowData.layout_position.get(i), i);

        Log.d("list_after", RowData.layout_position.toString());
        Log.d("list1", Integer.toString(highest_unchecked_pos));
        Log.d("list2", Integer.toString(original_pos));
        Log.d("list3", pos_map.toString());
        // sort layout pos
        Log.d("list_text",RowData.field1.toString());
        List<Integer>  layout_position_sorted = new ArrayList<>(RowData.layout_position);
        Collections.sort(layout_position_sorted);
        Log.d("list_text", layout_position_sorted.toString());
        // add all views in correct order
        for (int i = 0; i < RowData.layout_position.size(); i++) {

            int list_pos_ = pos_map.get(layout_position_sorted.get(i));
            Log.d("list_text1",Integer.toString(layout_position_sorted.get(i)));
            Log.d("list_text2",Integer.toString(list_pos_));
            tableLayout.addView(RowData.row_obj_list.get(list_pos_).tabelrow, i * 2);
            Log.d("list_text31",RowData.field1.get(list_pos_));
            tableLayout.addView(RowData.row_obj_list.get(list_pos_).line, (i * 2) +1);
        }

    }
}
