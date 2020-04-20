package com.example.flat_organizer.ui.main;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import com.example.flat_organizer.R;
import android.widget.TableRow;
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
        // get parent view

        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.TableLayout1);
        // generate new view object





        // texts

        final TextView text_field1 = (TextView) view.findViewById(R.id.text_field1);
        final TextView text_field2 = (TextView) view.findViewById(R.id.text_field2);
        final TextView text_field3 = (TextView) view.findViewById(R.id.text_field3);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("print1","print1");

                TableRow tr = new TableRow(getContext());
                TextView t1 = new TextView(getContext());
                TextView t2 = new TextView(getContext());
                TextView t3 = new TextView(getContext());
                t1.setText(text_field1.getText());
                t2.setText(text_field2.getText());
                t3.setText(text_field3.getText());
                t1.setTextSize(24);
                t2.setTextSize(24);
                t3.setTextSize(24);
                tr.addView(t1);
                tr.addView(t2);
                tr.addView(t3);
                tableLayout.addView(tr,0);

                View bl = new View(getContext());
                bl.setBackgroundColor(Color.parseColor("#070707"));
                bl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 2));
                tableLayout.addView(bl,0);


                //View.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                //text.setText("test");

            }
        });


        return view;
    }
}
