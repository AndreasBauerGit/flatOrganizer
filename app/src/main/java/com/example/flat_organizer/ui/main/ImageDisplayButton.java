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

public class ImageDisplayButton extends F2 implements View.OnClickListener  {
    public Button button;
    public Context context;
    public Activity activity;
    public ConstraintLayout cl;
    public View lv;
    public ImageView iv;
    public int call_id;
    private int reqCode = 1; // when intent from start activitz extits, this int is automatically passed to onActivityResult
 // "identifies where the request comes from"
    Uri imageURI;


    public ImageDisplayButton(Button b, ImageView i, ConstraintLayout cl, View v, Context c, Activity a){
        button = b;
        cl = cl;
        iv = i;
        lv = v;
        context = c;
        button.setOnClickListener(this);
        activity = a;



    }
    private void openImage(){
        Intent intent = new Intent();
        intent.setData(Uri.parse("content://media/internal/images/media"));
        intent.setAction(Intent.ACTION_GET_CONTENT);
        Log.d("setImage","33");
        activity.startActivityForResult(intent, reqCode);  //acitivity.startActivityForResult calls
        // the on Actitivty result method from main acitivity ?
        // just actitivty from
    }

    @Override
    public void onClick(View v) {
        Log.d("button","second");
        openImage();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        Log.d("setImage","3388");
        super.onActivityResult(requestCode,requestCode,data); // whz is this here?

        // result_ok is standard code (-1) send when activity exited with good result
        if (requestCode == reqCode && resultCode == Activity.RESULT_OK){
            Log.d("setImage","success1");
            imageURI = data.getData();
            iv.setImageURI(imageURI);
            Log.d("setImage","success2");

        } else {
            Log.d("setImage","fail");
        }

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
