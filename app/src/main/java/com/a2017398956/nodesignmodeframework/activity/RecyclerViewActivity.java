package com.a2017398956.nodesignmodeframework.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.ConvertTool;
import com.nfl.libraryoflibrary.view.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends BaseActivity {

    private RecyclerView recyclerView ;
    private MyRecyclerViewDivider myRecyclerViewDivider ;
    private TestRecyclerViewAdapter adapter ;
    private List<Map<String , String>> data = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1 :
                    break;
            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        myRecyclerViewDivider = new MyRecyclerViewDivider(new ColorDrawable(0xeeeeeeee), OrientationHelper.VERTICAL) ;
        //单位:px
        myRecyclerViewDivider.setMargin(50, 50, 50, 50);
        myRecyclerViewDivider.setHeight(ConvertTool.dp2px(1));
        recyclerView.addItemDecoration(myRecyclerViewDivider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Map<String , String> map ;
        for(int i = 0 ; i < 20  ; i++){
            map = new HashMap<>() ;
            map.put("title" , "item" + i) ;
            data.add(map) ;
        }
        adapter = new TestRecyclerViewAdapter(this , data) ;
        recyclerView.setAdapter(adapter);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.get(0).put("title" , "alert") ;
                adapter.notifyDataSetChanged();
            }
        } , 2000) ;
    }

    class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.VH>{

        private Context context ;
        private List<Map<String , String>> data ;

        public TestRecyclerViewAdapter(Context context , List<Map<String , String>> data){
            this.context = context ;
            this.data = data ;
        }
        @Override
        public TestRecyclerViewAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1 , null)) ;
        }

        @Override
        public void onBindViewHolder(TestRecyclerViewAdapter.VH holder, int position) {
            holder.tv_title.setText(data.get(position).get("title"));
        }

        @Override
        public int getItemCount() {
            return null == data ? 0 : data.size() ;
        }

        class VH extends RecyclerView.ViewHolder{
            TextView tv_title ;
            public VH(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(android.R.id.text1) ;
            }
        }
    }

    class MyRecyclerViewDivider extends RecyclerView.ItemDecoration{

        private Drawable mDivider;
        private int leftMargin, rightMargin, topMargin, bottomMargin;
        private int width, height;
        private int mOrientation;

        public MyRecyclerViewDivider(Drawable divider, int orientation) {
            setDivider(divider);
            setOrientation(orientation);
        }

        private void setDivider(Drawable divider) {
            this.mDivider = divider;
            if (mDivider == null) {
                mDivider = new ColorDrawable(0x808080);
            }
            width = mDivider.getIntrinsicWidth();
            height = mDivider.getIntrinsicHeight();
        }

        private void setOrientation(int orientation) {
            if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        private void setMargin(int left, int top, int right, int bottom) {
            this.leftMargin = left;
            this.topMargin = top;
            this.rightMargin = right;
            this.bottomMargin = bottom;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                drawHorizontal(c, parent);
            } else {
                drawVertical(c, parent);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop() + topMargin;
            final int bottom = parent.getHeight() - parent.getPaddingBottom() - bottomMargin;

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin + leftMargin;
                final int right = left + width;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft() + leftMargin;
            final int right = parent.getWidth() - parent.getPaddingRight() - rightMargin;

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin + topMargin;
                final int bottom = top + height;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                outRect.set(0, 0, leftMargin + width + rightMargin, 0);
            } else {
                outRect.set(0, 0, 0, topMargin + height + bottomMargin);
            }
        }
    }
}
