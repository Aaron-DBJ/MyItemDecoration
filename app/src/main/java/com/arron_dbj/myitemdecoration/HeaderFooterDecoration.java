package com.arron_dbj.myitemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class HeaderFooterDecoration extends RecyclerView.ItemDecoration {
    private List<String> mList;
    public HeaderFooterDecoration(List<String> list) {
        super();
        mList = list;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildAdapterPosition(view);
        int itemCount = mList.size();
        if (index == 0){
            outRect.top = 0;
            outRect.bottom = 20;
            outRect.left = 0;
            outRect.right = 0;
        } else if (index == itemCount + 1){
            outRect.top = 20;
            outRect.bottom = 0;
            outRect.left = 0;
            outRect.right = 0;
        }else {
            outRect.top = 20;
            outRect.bottom = 20;
            outRect.left = 40;
            outRect.right = 40;
        }
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
