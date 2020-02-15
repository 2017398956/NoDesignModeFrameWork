package nfl.com.androidart.chapter03.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.nfl.libraryoflibrary.utils.LogTool;

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {

    private float startX = 0;
    private float startY = 0;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogTool.i("CustomTextView's dispatchTouchEvent before");
        // 在父类的方法中调用了 onTouchEvent , 如果不加上这句，该 View 不能触发触摸操作
        boolean result = super.dispatchTouchEvent(event);
        LogTool.i(result ? "CustomTextView dispatchTouchEvent 保持该事件" : "CustomTextView dispatchTouchEvent 分发该事件");
        LogTool.i("CustomTextView's dispatchTouchEvent after");
        return true ;
    }

    /**
     *
     * @param event
     * @return true 使用该事件，false 不使用该事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event) ;
        LogTool.i(result ? "CustomTextView's onTouchEvent 保持该事件" : "CustomTextView's onTouchEvent 分发该事件");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogTool.i("CustomTextView's ACTION_DOWN");
                calculatePoint(2, event);
                break;
            case MotionEvent.ACTION_MOVE:
                LogTool.i("CustomTextView's ACTION_MOVE");
                excAction(2, event);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                LogTool.i("不是常用的三种命令");
                break;
        }
        return result ;
    }

    private void calculatePoint(int type, MotionEvent event) {
        switch (type) {
            case 1:
                // 通过 scrollTo 移动 View 的内容
                startX = event.getX() + getScrollX();
                startY = event.getY() + getScrollY();
                break;
            case 2:
                // 通过 setTranslation() 设置 View 在屏幕的位置；不要使用 event.getX() 会导致抖动
                startX = event.getRawX() - getTranslationX();
                startY = event.getRawY() - getTranslationY();
                break;
        }
    }

    private void excAction(int type, MotionEvent event) {
        switch (type) {
            case 1:
                // 通过 scrollTo 移动 View 的内容
                scrollTo((int) (startX - event.getX()), (int) (startY - event.getY()));
                break;
            case 2:
                // 通过 setTranslation() 设置 View 在屏幕的位置；不要使用 event.getX() 会导致抖动
                setTranslationX(event.getRawX() - startX);
                setTranslationY(event.getRawY() - startY);
                break;
        }
    }
}
