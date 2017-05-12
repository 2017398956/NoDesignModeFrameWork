package nfl.com.androidart.chapter02.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;

import java.util.List;

import nfl.com.androidart.R;
import nfl.com.androidart.chapter02.aidl.Book;
import nfl.com.androidart.chapter02.aidl.IBookManager;
import nfl.com.androidart.chapter02.service.BookManagerService;

public class Chapter02Activity extends CommonActionBarActivity {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(!service.isBinderAlive()){
                // 如果服务端 Binder 死亡这里直接退出
                LogTool.i("BookManagerServie dead") ;
                // TODO 重新绑定远程 Service
                return ;
            }
            IBookManager bookManager = IBookManager.Stub.asInterface(service) ;

//            IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
//                @Override
//                public void binderDied() {
//                }
//            } ;
//            if(null == bookManager){
//                bookManager.asBinder().unlinkToDeath(mDeathRecipient , 0) ;
//                return;
//            }

            try {
                List<Book> list = bookManager.getBookList() ;
                LogTool.i("query book list , list type: " + list.getClass()) ;
                //
                LogTool.i("query book list : " + list.toString()) ;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter02);
        Intent mService = new Intent(this , BookManagerService.class) ;
        bindService(mService , mConnection , BIND_AUTO_CREATE) ;
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
