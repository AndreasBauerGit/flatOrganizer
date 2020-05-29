package com.example.flat_organizer.ui.main;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

//import static androidx.core.app.ActivityCompat.getA; // this correct??

public class ImageDisplayButton extends F2 implements View.OnClickListener {
    public Button button;
    public Context context;

    public ConstraintLayout cl;



    public ImageDisplayButton(Button b, ConstraintLayout cl, Context c){
        button = b;
        context = c;
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }


    /*
    public void getImage(){

        try{
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivityForResult(i, RESULT_LOAD_IMAGE);
        }catch(Exception exp){
            Log.d("Error",exp.toString());

    }
     */
}
