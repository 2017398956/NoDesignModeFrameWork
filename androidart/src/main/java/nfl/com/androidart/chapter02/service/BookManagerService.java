package nfl.com.androidart.chapter02.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.nfl.libraryoflibrary.utils.LogTool;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nfl.com.androidart.chapter02.aidl.Book;
import nfl.com.androidart.chapter02.aidl.IBookManager;

public class BookManagerService extends Service {

    private static final String TAG = "SMS";
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };

    public BookManagerService() {
        LogTool.i("BookManagerService()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogTool.i("BookManagerService.onCreate()");
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "IOS"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogTool.i("BookManagerService.onBind()");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogTool.i("BookManagerService.onDestroy()");
    }
}
