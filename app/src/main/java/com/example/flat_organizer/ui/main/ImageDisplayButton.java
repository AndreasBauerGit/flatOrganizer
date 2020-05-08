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

//import static androidx.core.app.ActivityCompat.getA; // this correct??

public class ImageDisplayButton implements View.OnClickListener  {
    public Button button;
    public Context context;
    public Activity activity;
    public int call_id;



    public ImageDisplayButton(Button b, Context c, Activity a){
        button = b;
        context = c;
        button.setOnClickListener(this);
        activity = a;



    }

    @Override
    public void onClick(View v) {
        Log.d("button","second");
        Intent intent = new Intent();
        intent.setData(Uri.parse("content://media/internal/images/media"));
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, call_id);

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
