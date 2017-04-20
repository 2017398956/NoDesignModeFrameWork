package nfl.com.androidart.contents.handing;

import android.view.View;

import com.nfl.libraryoflibrary.listener.CustomOnClickListener;
import com.nfl.libraryoflibrary.utils.ToastTool;

import nfl.com.androidart.R;

/**
 * Created by fuli.niu on 2017/4/19.
 */

public class ContentsViewController extends CustomOnClickListener{
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId() ;
        if(id == R.id.bn_test){
            ToastTool.showShortToast("测试按钮被点击");
        }
    }
}
