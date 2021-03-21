package com.example.flatOrganizer.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;

import com.example.flatOrganizer.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RowData {

    public static List<String>  field1 = new ArrayList<>();
    public static List<String>  field2 = new ArrayList<>();
    public static List<String>  field3 = new ArrayList<>();
    public static List<Boolean>  checked = new ArrayList<>();
    public static List<Date>  time = new ArrayList<>();
    public static List<BigDecimal>  price = new ArrayList<>();
    public static List<Boolean>  payed = new ArrayList<>();
    public static List<Integer>  layout_position = new ArrayList<>();
    public static List<Integer>  id_unique = new ArrayList<>();
    public static List<RowLayoutManager> row_obj_list = new ArrayList<>(); // list of all LayoutObjects
    public TableLayout tableLayout;


    public RowData(TableLayout layout){
        tableLayout = layout;

    }


    public static void addData(String text1, String text2, String text3, TableLayout tableLayout, Context context){
        int id = setUniqueId();
        int list_pos = id_unique.size() - 1;
       //2 Log.d("list_pos",  Integer.toString(list_pos));

        field1.add(text1);
        field2.add(text2);
        field3.add(text3);
        time.add(null);
        checked.add(false);


        RowLayoutManager row = new RowLayoutManager(tableLayout, list_pos, context);
        // change position of all other elements: // would
        for (int i = 0; i < RowData.layout_position.size(); i++){
            if (RowData.layout_position.get(i) >= 0) { // considers -1 for not shown
                RowData.layout_position.set(i, RowData.layout_position.get(i) + 1);
            }
        }
        RowData.layout_position.add(0);
        row_obj_list.add(row);

        int[]  list_pos_list = new int[10];
        for (int i = 0; i < row_obj_list.size(); i++){
            list_pos_list[i] = row_obj_list.get(i).list_pos;

        }
       // Log.d("list_pos",  Arrays.toString(list_pos_list));

        // price.add()
    }

    public static void loadData(){

    }

    public static void saveData(){

    }
    private static int setUniqueId(){
        int id;

        if (id_unique.size() >0) {
            id = Collections.max(id_unique) + 1;
        } else {
            id = 0;
        }
        id_unique.add(id);
        return id;
    }

}



