package com.open.project.ui.Activity;

/**
 * Created by Dell on 5/17/2017.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObserveableScrollView extends ScrollView {

    private ScrollInterface scrollViewListener = null;

    public ObserveableScrollView(Context context) {
        super(context);
    }

    public ObserveableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObserveableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollInterface scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if(scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

}