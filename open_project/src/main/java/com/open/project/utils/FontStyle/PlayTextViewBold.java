package com.open.project.utils.FontStyle;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class PlayTextViewBold extends TextView {

    public PlayTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PlayTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayTextViewBold(Context context) {
        super(context);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/GothamRounded_Bold.otf");
            setTypeface(tf);
            //setFontType(tf);
        }
    }

}
