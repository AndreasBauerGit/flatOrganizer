
package com.example.flatOrganizer.ui.main;
import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import java.lang.reflect.Field;

public class CustomViewPager extends ViewPager {

    public SectionsPagerAdapter mAdapter; // this is populated with addAdapterRef in MainActivity
   // contains references to the two fragments and thus all objects in the fragments....
    // currently not used though..
    public CustomViewPager(Context context) {
        super(context);
        setMyScroller();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMyScroller();
    }


    public void addAdapterRef(PagerAdapter adapt){
         mAdapter = (SectionsPagerAdapter) adapt;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // dont really understand why this works ...

        // Never allow swiping to switch between pages
        Log.d("touch intercept","touch intercept");
        //Log.d("adapter", mAdapter.frag2.test);
        //Log.d("adapter", mAdapter.frag2.im_view_list.get(0).getViewArea().toString());
        return false;
    }
    //remaining code from https://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s
    // can probably be left out

    private void setMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/);
        }
    }
    }
