package nfl.com.androidart.chapter03.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.nfl.libraryoflibrary.utils.LogTool;

public class CustomLinearLayout extends LinearLayout {
    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogTool.i("-------------------- 我是分割线 --------------------");
        LogTool.i("CustomLinearLayout's dispatchTouchEvent before");
        boolean result = super.dispatchTouchEvent(ev);
        LogTool.i(result ? "CustomLinearLayout dispatchTouchEvent 保持该事件" : "CustomLinearLayout dispatchTouchEvent 分发该事件");
        LogTool.i("CustomLinearLayout's dispatchTouchEvent after");
        return result;
    }

    /**
     * 只有 ViewGroup 才有这个方法，View 中没有
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogTool.i("CustomLinearLayout's onInterceptTouchEvent before");
        boolean result = super.onInterceptTouchEvent(ev);
        LogTool.i(result ? "CustomLinearLayout onInterceptTouchEvent 拦截了触摸事件" : "CustomLinearLayout onInterceptTouchEvent 没拦截触摸事件");
        LogTool.i("CustomLinearLayout's onInterceptTouchEvent after");
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogTool.i("CustomLinearLayout's onTouchEvent before");
        boolean result = super.onTouchEvent(event);
        LogTool.i(result ? "CustomLinearLayout's onTouchEvent 保持该事件" : "CustomLinearLayout's onTouchEvent 分发该事件");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogTool.i("CustomLinearLayout's ACTION_DOWN");
                break;
            default:
                break;
        }
        LogTool.i("CustomLinearLayout's onTouchEvent after");
        return result;
    }

    /**
     * 可以干预事件是否拦截
     *
     * @param disallowIntercept
     */
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
