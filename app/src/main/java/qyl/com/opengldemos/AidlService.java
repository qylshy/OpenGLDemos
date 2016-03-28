package qyl.com.opengldemos;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by qiuyunlong on 16/3/22.
 */
public class AidlService extends Service {

    private final IaidlData.Stub mBinder = new IaidlData.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPid() throws RemoteException {
            return android.os.Process.myPid();
        }

        @Override
        public String getName() throws RemoteException {
            return "啊啊啊啊啊啊啊";
        }

        @Override
        public Data getData() throws RemoteException {
            Data data = new Data();
            data.id = 1;
            data.name = "hhhh";
            return data;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
