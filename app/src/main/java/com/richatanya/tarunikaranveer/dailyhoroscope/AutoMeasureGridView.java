package com.richatanya.tarunikaranveer.dailyhoroscope;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by parthverma on 19/03/17.
 */
public class AutoMeasureGridView extends GridView {


    public AutoMeasureGridView(Context context) {
        super(context);
    }

    public AutoMeasureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMeasureGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            HoroscopeCardAdapter adapter = (HoroscopeCardAdapter) getAdapter();

            int numColumns = 2;
            GridViewItemLayout.initItemLayout(numColumns, adapter.getCount());

            int columnWidth = getMeasuredWidth() / numColumns;
            adapter.measureItems(columnWidth);
        }
        super.onLayout(changed, l, t, r, b);
    }
}