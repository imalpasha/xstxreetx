package com.open.project.ui.Activity.AdvanceSearch;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.open.project.R;
import com.open.project.ui.Activity.ObserveableScrollView;
import com.open.project.ui.Activity.ScrollInterface;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter;


public class AdvanceSearchFragment extends DialogFragment {

    @Bind(R.id.scroll)
    ScrollView scroll;

    @Bind(R.id.imageView)
    ImageView imageView;


    @Bind(R.id.advanceSearchTopLayout)
    LinearLayout advanceSearchTopLayout;

    float alpha = 1.0f;
    float newAlpha = 1.0f;
    int overallXScroll = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogAnimation);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public int getTheme() {
        return R.style.DialogAnimation;
    }


    private ArrayList<SimpleSectionedListAdapter.Section> sections = new ArrayList<SimpleSectionedListAdapter.Section>();

    public static AdvanceSearchFragment newInstance() {
        AdvanceSearchFragment fragment = new AdvanceSearchFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_layout, container, false);
        ButterKnife.bind(this, view);

        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                float alpha = (float) scroll.getScrollY() / advanceSearchTopLayout.getHeight();
                imageView.setAlpha(newAlpha);
                imageView.setBackgroundColor(getColorWithAlpha(alpha, getResources().getColor(R.color.white)));
            }
        });

        return view;
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    private void sendResult() {
        if (getTargetFragment() == null) {
            Log.e("Get Target Fragment", "NULL");
            return;
        }

        Intent intent = new Intent();

        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, intent);
        dismiss();
    }
}
