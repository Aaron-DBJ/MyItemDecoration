package com.arron_dbj.myitemdecoration;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class StickyHeaderDecoration extends RecyclerView.ItemDecoration {
    private static final int headerHeight = 220;
    private static final int divider = 30;
    private static final int LEFT = 30;
    private static final int RIGHT = 30;

    private Paint mPaint;
    private Paint textPaint;
    private Province province;
    private OnProvinceListener onProvinceListener;

    public StickyHeaderDecoration(OnProvinceListener onProvinceListener) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setFakeBoldText(true);
        this.onProvinceListener = onProvinceListener;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildAdapterPosition(view);
        if (onProvinceListener != null) {
            province = onProvinceListener.getProvinceInfo(index);
            if (province.isFirstCityInProvince()) {
                outRect.top = headerHeight;
                outRect.left = LEFT;
                outRect.right = RIGHT;
            } else {
                outRect.top = divider;
                outRect.left = LEFT;
                outRect.right = RIGHT;
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        View child;
        for (int i = 0; i<childCount; i++){
            child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);

            if (onProvinceListener != null){
                province = onProvinceListener.getProvinceInfo(position);
                int provinceLeft = child.getLeft() - LEFT;
                int provinceRight = child.getRight() + RIGHT;
                if ( i != 0){
                    if (province.isFirstCityInProvince()){
                        textPaint.setColor(Color.parseColor("#dddddd"));
                        int top = child.getTop() - headerHeight;
                        int bottom = child.getTop() ;

                        drawHeader(province, c, provinceLeft, top, provinceRight, bottom);
                    }
                }else {
                    int top = parent.getPaddingTop();
                    textPaint.setColor(Color.BLACK);
                    if (province.isLastCityInProvince()){
                        int supposedTop = child.getBottom() - headerHeight;
                        if (supposedTop < top){
                            top = supposedTop;
                        }
                    }
                    int bottom = top + headerHeight ;

                    drawHeader(province, c, provinceLeft, top, provinceRight, bottom);
                }
            }
        }

    }



    private void drawHeader(Province province, Canvas canvas, int left, int top, int right, int bottom){
        int color = Color.parseColor(province.getProvinceBackground());
        mPaint.setColor(color);
        canvas.drawRect(left, top, right, bottom, mPaint);
        Bitmap bitmap = province.getProvinceBitmap();
        canvas.drawBitmap(bitmap, left, top, mPaint);
        canvas.drawText(province.getProvinceName(), left + 500, top + 120, textPaint);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

}
