package com.example.flatOrganizer.ui.main;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent; // how to acces single variable/atribute from class???
import android.view.ScaleGestureDetector;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class ImageDisplay extends F2 implements View.OnTouchListener {

    public Uri im_uri;
    public float mLastTouchX;
    public float mLastTouchY;
    public int mActivePointerId;
    public float mPosX = 0;
    public float mPosY = 0;
    public ImageView im_view;
    public ConstraintLayout layout;
    public ScaleGestureDetector mScaleDetector;
    public float mScaleFactor;
    public Context context;

    ConstraintLayout.LayoutParams lps;

    public ImageDisplay(ConstraintLayout l, Context c) {
        layout = l;
        context = c;
        // adds new image view, positions in center, sets content and defines initial margins
        im_view = createImageView(); // somhow i need to return im_view specifically when im in the constructor
        Log.d("imview3", im_view.toString());
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener()); // handles scaling
        mScaleFactor = im_view.getScaleX();


    }
    public ImageView createImageView(){
        Log.d("layout4", layout.toString());
        ImageView im_view = new ImageView(context);
        im_view.setId(View.generateViewId());
        layout.addView(im_view);

        // moving to center with layout margins zero to all sides
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(im_view.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
        constraintSet.connect(im_view.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);
        constraintSet.connect(im_view.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
        constraintSet.connect(im_view.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(layout);


        return im_view;
    }

    public void set_uri(Uri uri) {
        // separately called in onActivityResult
        im_uri = uri;
        im_view.setImageURI(im_uri);
        im_view.setOnTouchListener(this);
        // set image
    }





    public List<Integer> getViewArea(){

        List<Integer> area = new ArrayList<>();
        area.add(im_view.getLeft());
        area.add(im_view.getRight());
        area.add(im_view.getTop());
        area.add(im_view.getBottom());
        return area;
    }


    // scaling
    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            Log.d("scaling",Float.toString(mScaleFactor));
            im_view.setScaleX(mScaleFactor);
            im_view.setScaleY(mScaleFactor);
            im_view.invalidate(); // does this work?
            return true;
        }
    }

    public void moveView(View view, int dx, int dy){

        view.setTranslationX(dx);
        view.setTranslationY(dy);

    }

    // also interesting: onlongtap, on doubletap

    @Override
    public boolean onTouch(View view, MotionEvent ev) {  // what view do i need here??
        // Let the ScaleGestureDetector inspect all events.
        //mScaleDetector.onTouchEvent(ev);

        // ACTION_DOWN: first finger touches screen
        // ACTIO_UP: last finger leaves screen
        // ACTION_POINTER_DOWN: another finger touches the screen
        // ACTION_POINTER_UP: a finger, not the last one (!) leaves the screen
        // ACTION_MOVE: movement in x y direction


        final int action = ev.getActionMasked();


        // only scale when tow fingers are on the screen--> might be better solution to this
        // if (ev.getPointerCount() == 2):{
        //    mScaleDetector.onTouchEvent(ev);
        // }
        mScaleDetector.onTouchEvent(ev); // handles scaling (=zooming in and out events)

        if (mScaleDetector.isInProgress()){
            return true;
        }

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

                moveView(view, (int) mPosX,  (int) mPosY);
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