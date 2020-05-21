package com.example.flat_organizer.ui.main;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent; // how to acces single variable/atribute from class???
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.flat_organizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImageDisplay extends F2 implements View.OnTouchListener {

    public URL im_url;
    public float mLastTouchX;
    public float mLastTouchY;
    public int mActivePointerId;
    public float mPosX;
    public float mPosY;
    public ImageView im_view;
    ConstraintLayout.LayoutParams lps;

    public ImageDisplay(URL url,ImageView imageView) {
        im_url = url;
        im_view = imageView;
        imageView.setOnTouchListener(this);
        ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        mPosX = lps.leftMargin;
        mPosY = lps.topMargin;


    }
    public List<Integer> getViewArea(){

        List<Integer> area = new ArrayList<>();
        area.add(im_view.getLeft());
        area.add(im_view.getRight());
        area.add(im_view.getTop());
        area.add(im_view.getBottom());
        return area;
    }


    @Override
    public boolean onTouch(View view, MotionEvent ev) {  // what view do i need here??
        // Let the ScaleGestureDetector inspect all events.
        //mScaleDetector.onTouchEvent(ev);

        final int action = ev.getActionMasked();
        Log.d("onTouch1","works");
        Log.d("onTouch2",Integer.toString(action));
        Log.d("onTouch2",Float.toString(ev.getRawX()));
        Log.d("onTouch2",Float.toString(ev.getRawY()));


        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = ev.getActionIndex();
                final float x = ev.getRawX();
                final float y = ev.getRawY();

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = ev.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex = ev.findPointerIndex(mActivePointerId);

                final float x = ev.getRawX();
                final float y = ev.getRawY();

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;


                ConstraintLayout.LayoutParams lps = (ConstraintLayout.LayoutParams) view.getLayoutParams(); // why do i actually need this in every execution
                lps.leftMargin = (int) mPosX;
                lps.topMargin = (int) mPosY;
                view.setLayoutParams(lps);

                view.invalidate(); // should trigger redrawing of the view

                Log.d("layout_pos",Integer.toString(lps.leftMargin));
                Log.d("layout_pos",Integer.toString(lps.topMargin));


                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = ev.INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = ev.INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = ev.getActionIndex();
                final int pointerId = ev.getPointerId(pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0; // what does this do??
                    mLastTouchX = ev.getRawX();
                    mLastTouchY = ev.getRawY();
                    mActivePointerId = ev.getPointerId(newPointerIndex);

                }
                break;
            }
        }
        return true;
    }

}


//TODO: fix tab swiping
//TODO: add new image display objects
// TODO: scaling
// TODO copy to app specific storage