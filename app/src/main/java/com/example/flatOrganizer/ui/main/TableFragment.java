package com.example.flatOrganizer.ui.main;
import java.util.List;
import java.util.ArrayList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

//import com.example.flatOrganizer.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;

import com.example.flatOrganizer.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public String test = "test";
    public TableLayout tableLayout;
    public List tr_ids;

    //private CheckBox cb1;
    public TableFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */

    public static TableFragment newInstance(String param1, String param2) {
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f1, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.TableLayout1);
        // generate new view object

        // final List<RowLayoutManager> row_obj_list = new ArrayList<RowLayoutManager>(); // list of all LayoutObjects


        final TextView tf1 = (TextView) view.findViewById(R.id.text_field1);
        final TextView tf2 = (TextView) view.findViewById(R.id.text_field2);
        final TextView tf3 = (TextView) view.findViewById(R.id.text_field3);

        // load data fo the rows from firebase

        RowData.loadData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text1 = tf1.getText().toString();
                String text2 = tf2.getText().toString();
                String text3 = tf3.getText().toString();
                RowData.addData(text1, text2, text3, tableLayout, getContext());


            }
        });


        return view;
    }
}
