package nfl.com.androidart.chapter02.aidl;

/**
 * Created by fuli.niu on 2017/5/12.
 * 这个类是 IBookManager.aidl 自动生成的 IBookManager.java 的副本，为了方便学习 Binder ， 不要使用该类
 */
public interface IBookManager2 extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements nfl.com.androidart.chapter02.aidl.IBookManager2 {

        // Binder 的唯一标识，一般是当前 Binder 的类名
        private static final java.lang.String DESCRIPTOR = "nfl.com.androidart.chapter02.aidl.IBookManager2";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an nfl.com.androidart.chapter02.aidl.IBookManager2 interface,
         * generating a proxy if needed.
         * 用于将服务端的 Binder 对象转换成客户端所需的 AIDL 接口类型的对象，这种转换过程是区分进程的，如果
         * 客户端和服务端在同一个进程，此方法返回的就是服务端的 Stub 对象本身，否则返回的是系统封装后的 Stub.Proxy 对象。
         */
        public static nfl.com.androidart.chapter02.aidl.IBookManager2 asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof nfl.com.androidart.chapter02.aidl.IBookManager2))) {
                return ((nfl.com.androidart.chapter02.aidl.IBookManager2) iin);
            }
            return new nfl.com.androidart.chapter02.aidl.IBookManager2.Stub.Proxy(obj);
        }

        // 返回当前 Binder 对象
        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        /**
         * 运行在服务端的 Binder 线程池中，当客户发起跨进程请求时，远程请求会通过系统底层封装后交由此方法处理。
         * 服务端通过 code 可以确定客户端所请求的目标方法是什么，接着从 data 中取出目标方法所需的参数，然后执行该方法。
         * 当目标方法执行完毕后，就向 reply 写入返回值（如果此方法有返回值的话），到此 onTransact 方法执行完毕。
         * 需要注意的是此方法的返回值是 boolean ，那么当客户端请求失败时，就会返回 false ，因此我们可以利用这个特性做权限验证，
         * 毕竟我们也不希望随便一个进程都能远程调用我们的服务。
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getBookList: {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List<nfl.com.androidart.chapter02.aidl.Book> _result = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                }
                case TRANSACTION_addBook: {
                    data.enforceInterface(DESCRIPTOR);
                    nfl.com.androidart.chapter02.aidl.Book _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = nfl.com.androidart.chapter02.aidl.Book.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addBook(_arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements nfl.com.androidart.chapter02.aidl.IBookManager2 {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * Demonstrates some basic types that you can use as parameters
             * and return values in AIDL.
             *///    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

            /**
             * 该方法运行在客户端，当客户端远程调用时，它们内部实现是这样的：创建该方法所需的输入型 Parcel 对象 _data 、
             * 输出型 Parcel 对象 _reply 和返回值对象，然后把该方法的参数信息写入 _data 中（如果有参数的话）；
             * 接着调用 transact 方法来发起 RPC（远程过程调用）请求，同时当前线程挂起；然后服务端的 onTransact 方法会被调用，
             * 直到 RPC 过程返回后，当前线程继续执行，并从 _reply 中取出 RPC 过程的返回结果（如果有返回值的话），
             * 最后返回 _reply 中的数据。
             * @return
             * @throws android.os.RemoteException
             */
            @Override
            public java.util.List<nfl.com.androidart.chapter02.aidl.Book> getBookList() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<nfl.com.androidart.chapter02.aidl.Book> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(nfl.com.androidart.chapter02.aidl.Book.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            // 同 getBookList()
            @Override
            public void addBook(nfl.com.androidart.chapter02.aidl.Book book) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((book != null)) {
                        _data.writeInt(1);
                        book.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        /**
         * 这两个 id 用于标识在 transact 过程中客户端所请求的到底是哪个方法
         */
        static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public java.util.List<nfl.com.androidart.chapter02.aidl.Book> getBookList() throws android.os.RemoteException;

    public void addBook(nfl.com.androidart.chapter02.aidl.Book book) throws android.os.RemoteException;
}

