package com.example.flat_organizer.ui.main;
import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

//import com.example.flat_organizer.ui.login.LoginActivity;
import com.example.flat_organizer.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;

import com.example.flat_organizer.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F1 extends Fragment {
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
    public F1() {
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

    public static F1 newInstance(String param1, String param2) {
        F1 fragment = new F1();
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
        Button loginbutton=(Button) view.findViewById(R.id.loginButton);
        // get parent view

        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.TableLayout1);
        // generate new view object

        final List<RowObject> row_obj_list = new ArrayList<RowObject>(); // list of all table row objects

        //tr_ids.add(1);

        // texts
        //final Fragment fragment_parent = this;

        final TextView tf1 = (TextView) view.findViewById(R.id.text_field1);
        final TextView tf2 = (TextView) view.findViewById(R.id.text_field2);
        final TextView tf3 = (TextView) view.findViewById(R.id.text_field3);
        //final CheckBox check_box1  = (CheckBox) view.findViewById(R.id.checkBox);

       /* loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button Clicked");
                Intent loginActivityIntent = new Intent(getContext(), LoginActivity.class);
                startActivity(loginActivityIntent);
            }
        });*/

        //Log.d("test", R.field1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RowObject R = new RowObject(tf1.getText().toString(),tf2.getText().toString(),tf3.getText().toString(), getContext(),
                        tableLayout, row_obj_list);
                R.addRow();
                Log.d("print1","print1");





                //View.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                //text.setText("test");

            }
        });


        return view;
    }
}
